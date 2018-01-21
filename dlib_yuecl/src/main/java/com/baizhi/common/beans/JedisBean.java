package com.baizhi.common.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisBean {


    @Bean
    public Jedis getJedis() {

        return new Jedis("192.168.129.129", 6379);
    }


}
