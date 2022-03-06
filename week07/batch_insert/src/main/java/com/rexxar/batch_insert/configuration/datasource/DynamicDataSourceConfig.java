package com.rexxar.batch_insert.configuration.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.rexxar.batch_insert.datasource.DynamicDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DynamicDataSourceConfig {
    
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
    
    /**
     * 配置动态路由数据源Bean
     * @return DynamicDataSource
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put("master", masterDataSource());
        targetDataSources.put("slave", slaveDataSource());
        return new DynamicDataSource(masterDataSource(), targetDataSources);
    }
}
