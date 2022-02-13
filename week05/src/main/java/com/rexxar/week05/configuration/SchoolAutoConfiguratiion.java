package com.rexxar.week05.configuration;

import com.rexxar.week05.pojo.School;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(School.class)
public class SchoolAutoConfiguratiion {
    
    @Bean
    @ConditionalOnMissingBean
    public School create() {
        return new School();
    }
}
