package nyongnyong.pangparty.entity.feed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.common.ActivityType;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"uid", "eventUid", "mediaUid", "activityType", "activityTime"})
public class FeedActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne @MapsId
    @JoinColumn()
    private Member member;
    private Long eventUid;
    private Long mediaUid;
    private ActivityType activityType;
    private LocalDateTime activityTime;

}
