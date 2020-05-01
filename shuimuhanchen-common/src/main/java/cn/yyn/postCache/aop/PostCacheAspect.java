package cn.yyn.postCache.aop;

import cn.yyn.common.ThreadPoolUtil;
import cn.yyn.exception.BackupCacheKeyMissException;
import cn.yyn.exception.BackupCacheRedisException;
import cn.yyn.postCache.redisproxy.JedisProxy;
import cn.yyn.postCache.serializer.NonExistRedisObject;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

@Aspect
@Component
@Slf4j
public class PostCacheAspect {

    @Resource
    private JedisProxy jedisProxy;

    @Resource
    private ThreadPoolUtil threadPoolUtil;

    private static final Integer oneDaySeconds = 24 * 3600;

    @Around("@annotation(PostCache)")
    public Object around(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        String key;
        Object value;
        try {
            value = pjp.proceed(args);
            processResultAsync(method, args, value);
        } catch (Throwable t) { //业务逻辑未正常执行成功，去取缓存
            String errorMsg = "interface: " + method.getDeclaringClass().getSimpleName() + ", method: " + method.getName();
            try {
                Pair<String, Integer> keyAndTTL = getKeyAndTTL(method, args);
                key = keyAndTTL.getLeft();

                errorMsg = errorMsg + ", key: " + key;
                log.warn("invoke method error, failback triggered. " + errorMsg, t);

                value = jedisProxy.get(key);
            } catch (Exception e) { //缓存未取成功抛出降级异常
                log.error("invoke method error, get Redis post-cache error. " + errorMsg, e);
                throw new BackupCacheRedisException(errorMsg, e);
            }

            if(value instanceof NonExistRedisObject) {
                log.error("invoke method error, get Redis post-cache key missed. " + errorMsg);
                throw new BackupCacheKeyMissException(errorMsg, t);
            }
            log.warn("invoke method error, failback succeed. " + errorMsg + ", value:" + value);
            return value;    //取缓存
        }

        return value;
    }

    private Pair<String, Integer> getKeyAndTTL(Method method, Object[] args) {
        Class<?> declaringClass = method.getDeclaringClass();
        PostCacheConfig cacheConfigAnno = declaringClass.getAnnotation(PostCacheConfig.class);
        String prefix = StringUtils.EMPTY;
        int expire = oneDaySeconds;
        if (cacheConfigAnno != null) {
            prefix = cacheConfigAnno.prefix();
            expire = cacheConfigAnno.expire();
        }
        String key = getKey(prefix, method.getDeclaringClass().getName(), method.getName(), args);

        PostCache postCacheAnno = method.getAnnotation(PostCache.class);
        if (postCacheAnno != null && postCacheAnno.expire() > 0) {
            expire = postCacheAnno.expire();
        }
        return Pair.of(key, expire);

    }

    private String getKey(String prefix, String className, String methodName, Object[] args) {
        String argStr = SimpleKeyGenerator.generateKey(args).toString();
        return Joiner.on(":").join(prefix, className, methodName, argStr);
    }

    private void processResultAsync(Method method, Object[] args, Object value) {

        ThreadPoolExecutor executor = threadPoolUtil.getCustomExecutorService();

        executor.submit(new Runnable() {
            @Override
            public void run() {
                Pair<String, Integer> keyAndTTL = null;
                try {
                    keyAndTTL = PostCacheAspect.this.getKeyAndTTL(method, args);
                    String key = keyAndTTL.getLeft();
                    int expire = keyAndTTL.getRight();
                    jedisProxy.put(key, value, expire);
                } catch (Exception ex) { //如刷新备用缓存失败了，则清除原有缓存，避免脏数据
                    log.error("put cache failed. key:" + (keyAndTTL == null ? "" : keyAndTTL.getLeft()), ex);
                }
            }
        });
    }
}
