package cn.yyn.postCache.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PostCacheConfig {
    /**
     * 前缀，会拼在key的前面
     */
    String prefix() default "mls-backup-cache:";

    /**
     * 过期时间 秒，默认一天
     */
    int expire() default 24 * 3600;

}
