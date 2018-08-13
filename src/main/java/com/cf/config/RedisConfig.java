package com.cf.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration  
@EnableAutoConfiguration  
public class RedisConfig {

    @Bean  
    @ConfigurationProperties(prefix="spring.redis")  
    public JedisPoolConfig getRedisConfig(){
        //利用这个可以配置redis的一些参数.
        JedisPoolConfig config = new JedisPoolConfig();
        //config.setMaxTotal()这种方式可以配置一些参数.但是还是需要在配置文件里面配置一些redis基础链接参数.
        return config;  
    }  
      
    @Bean  
    @ConfigurationProperties(prefix="spring.redis")  
    public JedisConnectionFactory getConnectionFactory(){  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        JedisPoolConfig config = getRedisConfig();  
        factory.setPoolConfig(config);  
        System.out.println("JedisConnectionFactory bean init success.");  
        return factory;  
    }
    /*
    *  需要使用模板操作一些数据.这个是将redis模板交给speing管理.
    * */
    @Bean  
    public RedisTemplate<?, ?> getRedisTemplate(){  
        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());  
        return template;  
    }  
}
