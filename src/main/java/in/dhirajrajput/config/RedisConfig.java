package in.dhirajrajput.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // Use String serializer for the keys (common practice)
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // Using default serializer for the values
        redisTemplate.setValueSerializer(redisTemplate.getDefaultSerializer());

        return redisTemplate;
    }
}
