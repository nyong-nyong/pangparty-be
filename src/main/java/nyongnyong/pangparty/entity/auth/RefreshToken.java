package nyongnyong.pangparty.entity.auth;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@RedisHash("refreshToken")
public class RefreshToken {
    @Id
    private String id;

    @Indexed // 필드 값으로 데이터 찾을 수 있게 하는 어노테이션(findByAccessToken)
    private String username;

    @TimeToLive
    private Long expiration; // seconds

    @Builder
    public RefreshToken(String id, String username, Long expiration) {
        this.id = id;
        this.username = username;
        this.expiration = expiration;
    }
}