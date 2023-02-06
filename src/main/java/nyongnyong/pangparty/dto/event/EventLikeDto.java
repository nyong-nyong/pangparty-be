package nyongnyong.pangparty.dto.event;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventLikeDto {
    @Id
    private Long uid;
    @NonNull
    private Long eventUid;
    @NonNull
    private Long memberUid;
    private LocalDateTime likeTime;

    @Builder
    public EventLikeDto(Long uid, Long eventUid, Long memberUid, LocalDateTime likeTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberUid = memberUid;
        this.likeTime = likeTime;
    }
}
