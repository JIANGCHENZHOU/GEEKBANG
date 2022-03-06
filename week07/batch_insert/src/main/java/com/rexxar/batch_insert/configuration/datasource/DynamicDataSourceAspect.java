package com.rexxar.batch_insert.configuration.datasource;

import java.lang.reflect.Method;

import com.rexxar.batch_insert.datasource.DynamicDataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 以@DynaminDataSource为切点，根据方法是否为query，选择不同的datasource
 */
@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered{

    Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("@within(com.rexxar.batch_insert.configuration.datasource.DynaminDataSource)")
    public void dataSourcePointCut() {
    }
    

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        logger.info("-------------------------------------");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        boolean isQuery = method.getName().contains("query");
        
        logger.info("method.name = " + method.getName());

        if (isQuery) {
            DynamicDataSource.setDataSource("slave");
            logger.info("set datasource is slave");
        } else {
            DynamicDataSource.setDataSource("master");
            logger.info("set datasource is master");
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            logger.info("clean datasource");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
    
}
