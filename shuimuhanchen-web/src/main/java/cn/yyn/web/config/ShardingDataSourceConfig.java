package cn.yyn.web.config;

import cn.yyn.dao.interceptor.ShardTableInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源配置
 */
@Configuration
@MapperScan("cn.yyn.dao")
public class ShardingDataSourceConfig {

    @Bean
    public Interceptor shardTableInteceptor() {
        return new ShardTableInterceptor();
    }

}