package nyongnyong.pangparty.entity.feed;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.common.FeedType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "followeeUid", "feedType", "createTime"})
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne
    @JoinColumn()
    private FeedActivity feedActivity;

    @OneToOne
    @JoinColumn()
    private Post post;
    private Long followeeUid;
    private FeedType feedType;
    private LocalDateTime createTime;

}
