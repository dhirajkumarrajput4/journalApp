package in.dhirajrajput.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

// @Configuration
public class RedisConfig {
    // @Bean
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(connectionFactory);

    //     // Use String serializer for keys
    //     template.setKeySerializer(new StringRedisSerializer());

    //     // Use Jackson2JsonRedisSerializer for values
    //     Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        
    //     // Optional: Setup ObjectMapper for handling polymorphic types
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().build();
    //     objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

    //     serializer.setObjectMapper(objectMapper);
    //     template.setValueSerializer(serializer);
        
    //     // You can also set it for hash key/value if needed
    //     template.setHashKeySerializer(new StringRedisSerializer());
    //     template.setHashValueSerializer(serializer);

    //     template.afterPropertiesSet();
    //     return template;
    // }
}
