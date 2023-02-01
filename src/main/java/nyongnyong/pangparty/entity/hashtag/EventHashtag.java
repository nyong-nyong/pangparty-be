package nyongnyong.pangparty.entity.hashtag;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class EventHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AtomicLong uid;
    private AtomicLong eventUid;
    private AtomicLong hashtagUid;
    private LocalDateTime addTime;
}
