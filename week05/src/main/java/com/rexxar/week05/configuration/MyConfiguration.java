package com.rexxar.week05.configuration;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    
    public HikariDataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(100); 
        ds.setDataSourceClassName("com.mysql.jdbc.Driver"); 
        ds.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/test"); 
        ds.addDataSourceProperty("user", "root");
        ds.addDataSourceProperty("password", "123456");
        ds.addDataSourceProperty("cachePrepStmts", true); 
        ds.addDataSourceProperty("prepStmtCacheSize", 250); 
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048); 
        ds.addDataSourceProperty("useServerPrepStmts", true);
        return ds;
    }
}
