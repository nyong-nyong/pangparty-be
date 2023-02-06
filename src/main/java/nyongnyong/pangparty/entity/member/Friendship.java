package nyongnyong.pangparty.entity.member;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "followTime"})
public class Friendship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_uid")
    private Member follower;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_uid")
    private Member followee;
    private LocalDateTime followTime;

    @Builder
    public Friendship(LocalDateTime followTime) {
        this.followTime = followTime;
    }

}
