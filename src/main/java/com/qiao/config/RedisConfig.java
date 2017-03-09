package com.qiao.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.qiao.domain.models.User;

@Configuration
public class RedisConfig{
	
	
	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisConnectionFactory jedisConnectionFactory() {
	return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory,
	@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
	RedisTemplate<String, User> template = new RedisTemplate<String, User>();
	template.setConnectionFactory(jedisConnectionFactory);
	template.setKeySerializer(new StringRedisSerializer());
	template.setValueSerializer(new JdkSerializationRedisSerializer());
	return template;
	}

//	@Bean
//	private JedisConnectionFactory jedisConnectionFactory() {
//		return new JedisConnectionFactory();
//	}
//
//	@Bean
//	public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
//		RedisTemplate<String, User> template = new RedisTemplate<String, User>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new RedisObjectSerializer());
//		return template;
//	}
}
