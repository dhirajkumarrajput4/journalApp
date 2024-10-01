package in.dhirajrajput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    @SuppressWarnings("unchecked")
    @Test
    void testResitTemplate() {

        redisTemplate.opsForValue().set("email", "dhirajkumarrajput4@gmail.com");

        Object email = redisTemplate.opsForValue().get("email");

        System.out.println(email);

    }

}
