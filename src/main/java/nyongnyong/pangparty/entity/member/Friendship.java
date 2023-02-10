package nyongnyong.pangparty.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Friendship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_uid")
    @ToString.Exclude
    private Member follower;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_uid")
    @ToString.Exclude
    private Member followee;

    private LocalDateTime followTime;

    @Builder
    public Friendship(Member follower, Member followee, LocalDateTime followTime) {
        this.follower = follower;
        this.followee = followee;
        this.followTime = followTime;
    }

    //    @Builder
    public Friendship(LocalDateTime followTime) {
        this.followTime = followTime;
    }

}
