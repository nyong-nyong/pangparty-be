package nyongnyong.pangparty.entity.feed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.common.FeedType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private Long activityUid;
    private Long postUid;
    private Long followeeUid;

    private FeedType feedType;
    private LocalDateTime createTime;

}