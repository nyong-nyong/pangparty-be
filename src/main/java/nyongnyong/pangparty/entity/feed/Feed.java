package nyongnyong.pangparty.entity.feed;

import lombok.*;
import nyongnyong.pangparty.common.FeedType;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "feedType", "createTime"})
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_activity_uid")
    private FeedActivity feedActivity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_uid")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    @Enumerated(EnumType.STRING)
    private FeedType feedType;
    private LocalDateTime createTime;

    @Builder
    public Feed(FeedType feedType, LocalDateTime createTime) {
        this.feedType = feedType;
        this.createTime = createTime;
    }
}
