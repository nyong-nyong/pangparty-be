package nyongnyong.pangparty.entity.feed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.common.ActivityType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class FeedActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AtomicLong uid;

    private AtomicLong memberUid;
    private AtomicLong eventUid;
    private AtomicLong mediaUid;

    private ActivityType activityType;

    private LocalDateTime activityTime;

}
