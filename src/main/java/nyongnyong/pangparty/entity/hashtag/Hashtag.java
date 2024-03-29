package nyongnyong.pangparty.entity.hashtag;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "name", "createTime"})
public class Hashtag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String name;
    private LocalDateTime createTime;

    @Builder
    public Hashtag(String name, LocalDateTime createTime) {
        this.name = name;
        this.createTime = createTime;
    }
}
