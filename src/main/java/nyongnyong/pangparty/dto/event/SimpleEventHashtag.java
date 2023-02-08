package nyongnyong.pangparty.dto.event;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SimpleEventHashtag {
    @Id
    private Long uid;
//    @NonNull
//    private Long eventUid;
    @NonNull
    private Long hashtagUid;
    private LocalDateTime addTime;

    @Builder
    public SimpleEventHashtag(Long uid, Long hashtagUid, LocalDateTime addTime) {
        this.uid = uid;
        this.hashtagUid = hashtagUid;
        this.addTime = addTime;
    }
//    @Builder
//    public SimpleEventHashtag(Long uid, Long eventUid, Long hashtagUid, LocalDateTime addTime) {
//        this.uid = uid;
//        this.eventUid = eventUid;
//        this.hashtagUid = hashtagUid;
//        this.addTime = addTime;
//    }
}
