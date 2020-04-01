package cn.yyn.web.config;

import cn.yyn.common.CacheConstants;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
//CacheManager只要通过@EnableCaching注释启用缓存支持，Spring Boot将根据实现自动配置适当的配置。
//如果您使用的缓存基础结构与不是基于接口的bean，请确保启用该proxyTargetClass属性
@EnableCaching(proxyTargetClass = true)
@ImportResource("classpath:redisson.xml")
public class MyRedissionConfig {

    @Resource
    private RedissonClient redissonClient;

    @Bean
    CacheManager cacheManager() {

        Map<String, CacheConfig> config = new HashMap<>();
        CacheConfig cacheConfig = new CacheConfig();

        cacheConfig.setTTL(day(1));
        config.put(CacheConstants.DEFAULT, cacheConfig);

        return new RedissonSpringCacheManager(redissonClient, config);
    }

    @Bean("myKeyGenerator")
    //自定义缓存key生成器，若要使用，再注解中加入keyGenerator参数=myKeyGenerator即可
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName()+"["+ Arrays.asList(params).toString()+"]";
            }
        };
    }

    private int hour(int hour) {
        return hour * 60 * 60 * 1000;
    }

    private int day(int day) {
        return day * 24 * 60 * 60 * 1000;
    }

}
