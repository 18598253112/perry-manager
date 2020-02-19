package com.example.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration  
@EnableCaching  
public class RedisConfig extends CachingConfigurerSupport {  
  
	@Autowired
    private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
                .entryTtl(Duration.ofDays(30));
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

	/**
	  * @Description: 防止redis入库序列化乱码的问题
	  * @return     返回类型
	  * @date 2018/4/12 10:54
	  */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		//设置序列化  
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();  
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
        jackson2JsonRedisSerializer.setObjectMapper(om);
        
        RedisSerializer stringSerializer = new StringRedisSerializer();
	    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
	    redisTemplate.setConnectionFactory(redisConnectionFactory);
	    redisTemplate.setKeySerializer(stringSerializer);//key序列化
	    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);  //value序列化
	    redisTemplate.setHashKeySerializer(stringSerializer);//Hash key序列化  
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value序列化
	    redisTemplate.afterPropertiesSet();

	    return redisTemplate;
	}
}