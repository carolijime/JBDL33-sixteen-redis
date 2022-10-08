package com.example.JBDL33sixteenredis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class PersonConfig {

    /**
     *
     * How to install Redis in Windows:
     * https://redis.io/docs/getting-started/installation/install-redis-on-windows/
     * https://developer.redis.com/create/windows/
     * create account for redis on the cloud: https://app.redislabs.com/#/login
     * run redis (in Ubuntu): redis-server
     * run redis cli - local (in ubuntu, second open window): redis-cli
     * run redis cli - cloud (in ubuntu, second open window): redis-cli -h redis-14040.c9.us-east-1-4.ec2.cloud.redislabs.com -p 14040 -a qLWIcjofl1rRmidYlx7xdWRvhy57SlWjA
     */

    //function in case we could like to modularize our code, but we could be just adding it to the RedisTemplate function

    @Bean
    public LettuceConnectionFactory getConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration("redis-14040.c9.us-east-1-4.ec2.cloud.redislabs.com", 14040);

        redisStandaloneConfiguration.setPassword("qLWIcjofl1rRmidYlx7xdWRvhy57SlWj");

        LettuceConnectionFactory lettuceConnectionFactory
                = new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> getTemplate(){


        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //covert object (in this case Person) to array of byte[]

        redisTemplate.setConnectionFactory(getConnectionFactory());

        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }
}
