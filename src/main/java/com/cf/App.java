package com.cf;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cf.common.IndexFilter;
import com.cf.common.IndexListener;
import com.cf.common.IndexServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;


//@SpringBootApplication(exclude = MybattisAutoConfiguration.class),
// 不想用mybatis集成springboot的默认配置.所以需要自己配置
//springboot有两种配置方式.在application.properties中配置.推荐,不容易冲突.;另一种不推荐.
//springboot本身集成了servlet等等和web相关的东西.我们可以利用他的配置文件进行配置
@EnableTransactionManagement  // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication
//@ServletComponentScan 扫描自己是配置的过滤器,监听器和servlet
@MapperScan("com.cf.dao")//扫描dao接口
@EnableCaching  //(开启声明式 缓存支持,个人感觉用处不大...一共四个注解.chacheable(),cacheput(),cacheevice(删除),cache{组合具体百度或者看pdf})
//@SpringBootApplication  = @Configuration+   @EnableAutoConfiguration  + @ComponentScan(具体百度查)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /*
     * 注入fastGson,另一种方式启动类注(启动类注入不现实,需要继承)
     * */
    @Bean
    public HttpMessageConverters fastJsonConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastConfig = new FastJsonConfig();
        fastConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

    /*
     * 配置过滤器的bean,此种方式常用,而利用注解原生配置基本不用.所以我将@ServletComponentScan注解注释掉,使用bean注册方式.可以在filter里面做一个成员1变量去过滤不需要拦截的请求.
     * */
    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new IndexFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("IndexFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }
    /*
     * 配置第一个servlet.和过滤器同理.可以配置多个url拦截的请求
     * */
    @Bean
    public ServletRegistrationBean indexServletRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean();
        registration.setServlet(new IndexServlet());
        registration.addUrlMappings("/IndexServlet.do");
        registration.addUrlMappings("/IndexServlet1.do");
        registration.setName("IndexServlet");
        return registration;
    }
    /*
     * 注册后缀,*.do等等.可以注册多个后缀.
     * */
    @Bean
    public ServletRegistrationBean servletRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.addUrlMappings("*.do");
        registration.addUrlMappings("*.action");
        return registration;
    }
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new IndexListener());
        return servletListenerRegistrationBean;
    }
   /* @Bean  缓存的bean....cache,这个个人认为鸡肋......
    public EhCacheCacheManager cacheCacheManager(CacheManager cacheManager){
        return new EhCacheCacheManager();
    }*/
}
