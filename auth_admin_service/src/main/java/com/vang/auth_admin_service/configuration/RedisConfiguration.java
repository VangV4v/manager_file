package com.vang.auth_admin_service.configuration;

import com.vang.auth_admin_service.common.AuthAdminCommon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(AuthAdminCommon.IP, AuthAdminCommon.REDIS_PORT));
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setDefaultSerializer(StringRedisSerializer.UTF_8);
        return redisTemplate;
    }

}