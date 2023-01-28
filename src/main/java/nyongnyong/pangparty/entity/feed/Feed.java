package nyongnyong.pangparty.feed.domain;

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
    private AtomicLong uid;

    private AtomicLong activityUid;
    private AtomicLong postUid;
    private AtomicLong followeeUid;

    private FeedType feedType;
    private LocalDateTime createTime;

}
