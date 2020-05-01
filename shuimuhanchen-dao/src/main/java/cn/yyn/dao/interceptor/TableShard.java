package cn.yyn.dao.interceptor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableShard {
    String[] shardByKeys();

    String tableName();
}
