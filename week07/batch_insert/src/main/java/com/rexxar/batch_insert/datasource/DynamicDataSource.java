package com.rexxar.batch_insert.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态路由
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    //线程副本用于确定当前线程所用的dataSource
    private static final ThreadLocal<String> dataSourceContext = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceContext.get();
    }
    
    public static void setDataSource(String dataSource) {
        dataSourceContext.set(dataSource);
    }
    public static String getDataSource() {
        return dataSourceContext.get();
    }
    public static void clearDataSource() {
        dataSourceContext.remove();
    }
}
