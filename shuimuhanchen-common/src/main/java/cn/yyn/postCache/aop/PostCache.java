package cn.yyn.postCache.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//生命周期长度 SOURCE < CLASS < RUNTIME ，所以前者能作用的地方后者一定也能作用，
//如果需要在运行时去动态获取注解信息，那只能用 RUNTIME
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PostCache {
    int expire() default 0;
}
