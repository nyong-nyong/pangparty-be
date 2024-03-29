package nyongnyong.pangparty.entity.feed;

import lombok.*;
import nyongnyong.pangparty.common.ActivityType;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "activityType", "activityTime"})
public class FeedActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    private Event event;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_media_uid")
    private AlbumMedia albumMedia;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    private LocalDateTime activityTime;

    @Builder
    public FeedActivity(ActivityType activityType, LocalDateTime activityTime) {
        this.activityType = activityType;
        this.activityTime = activityTime;
    }
}
