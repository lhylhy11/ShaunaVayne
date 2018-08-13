package com.cf.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 手动配置静态资源路径
 *
 */
@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter{
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).
                setUseTrailingSlashMatch(true);
    }
}
