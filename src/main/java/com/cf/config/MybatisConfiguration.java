package com.cf.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
/*
 * mybatis配置.
 * @Configuration  告诉springboot这个是一个配置文件.需要进行配置
 * */
@Configuration
public class MybatisConfiguration {
    /*
     *  @Bean  这个注解将方法交给springboot使用
     *  方法作用:配置分页插件
     * */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundswithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
