package nyongnyong.pangparty.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(updatable = false)
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
