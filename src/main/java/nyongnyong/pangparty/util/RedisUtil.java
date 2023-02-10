package nyongnyong.pangparty.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final StringRedisTemplate stringRedisTemplate;

    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setValueWithExpiration(String key, String value, long expire) {
        Duration duration = Duration.ofSeconds(expire);
        stringRedisTemplate.opsForValue().set(key, value, duration);
    }

    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        stringRedisTemplate.delete(key);
    }

}
