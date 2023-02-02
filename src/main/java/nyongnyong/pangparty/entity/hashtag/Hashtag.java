package nyongnyong.pangparty.entity.hashtag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "name", "createTime"})
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String name;
    private LocalDateTime createTime;

    public Hashtag(String name, LocalDateTime createTime) {
        this.name = name;
        this.createTime = createTime;
    }
}
