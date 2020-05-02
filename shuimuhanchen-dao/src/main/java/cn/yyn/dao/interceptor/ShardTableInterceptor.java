package cn.yyn.dao.interceptor;

import cn.yyn.common.RouterUtil;
import cn.yyn.exception.RouterException;
import cn.yyn.model.entity.SysLog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import static org.apache.ibatis.reflection.SystemMetaObject.DEFAULT_OBJECT_FACTORY;
import static org.apache.ibatis.reflection.SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY;

/**
 * 基于mybatis的插件拦截器
 */
@Slf4j
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class ShardTableInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        String sqlId = mappedStatement.getId();

        String className = sqlId.substring(0, sqlId.lastIndexOf("."));
        Class<?> classObj = Class.forName(className);

        TableShard tableShard = classObj.getAnnotation(TableShard.class);
        if (null == tableShard || StringUtils.isBlank(tableShard.tableName()) || ArrayUtils.isEmpty(tableShard.shardByKeys())) {
            //不需要分表，直接传递给下一个拦截器处理
            return invocation.proceed();
        }

        //根据配置获取分表字段，生成分表SQL
        String suffix = genShardByValue(metaStatementHandler, tableShard);
        String newSql = boundSql.getSql().replace(tableShard.tableName(), tableShard.tableName() + "_" + suffix);

        metaStatementHandler.setValue("delegate.boundSql.sql", newSql);

        // 传递给下一个拦截器处理
        return invocation.proceed();
    }

    private String genShardByValue(MetaObject metaStatementHandler, TableShard tableShard) {

        Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");

        String[] shardKeys = tableShard.shardByKeys();

        for (String key : shardKeys) {
            String shardValue = parseParam(parameterObject, key);
            if (StringUtils.isNotBlank(shardValue)) {
                return RouterUtil.getTableSuffixByValue(shardValue);
            }
        }

        throw new RouterException("分表失败，未找到分表后缀");
    }

    /**
     * 解析mapper入参获取分表字段值
     * 1.获取key字段对应值
     * 2.根据值获得可路由的值
     */
    private String parseParam(Object parameterObject, String key) {
        Object fieldValue = null;

        //todo：传对象参数怎么办？
        if (parameterObject instanceof Map) {
            //使用@param注解传递对象字段参数
            Map paramMap = (Map) parameterObject;
            if (paramMap.containsKey(key)){
                fieldValue = paramMap.get(key);
            }
            //使用@param注解传递对象参数
            Object param1 = paramMap.get("param1");
            if (param1 instanceof SysLog) {
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(param1));
                fieldValue = jsonObject.get(key);
            }
        }else {
            //对象内部属性传参
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(parameterObject));
            if (jsonObject.containsKey(key)) {
                fieldValue = jsonObject.get(key);
            }
        }

        // 单字符串形式
        if (fieldValue instanceof String) {
            return (String) fieldValue;
        }
        // 集合形式
        if (fieldValue instanceof Collection) {
            return (String) ((Collection) fieldValue).iterator().next();
        }

        return StringUtils.EMPTY;
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否则直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        //获取xml plugin配置中的name属性值入下，这里我们NOP就可以了
    }
//    <configuration>
//      <plugins>
//		  <plugin interceptor="com.mybatis3.interceptor.MyBatisInterceptor">
//			<property name="value" value="100" />
//		  </plugin>
//	    </plugins>
//    </configuration>

}

