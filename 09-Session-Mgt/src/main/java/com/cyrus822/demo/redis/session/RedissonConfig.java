package com.cyrus822.demo.redis.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.redisson.api.RedissonClient;
import org.redisson.Redisson;
import org.redisson.config.Config;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
              .setAddress("redis://your-redis-host:6379")
              .setPassword("your-redis-password");
        return Redisson.create(config);
    }
}