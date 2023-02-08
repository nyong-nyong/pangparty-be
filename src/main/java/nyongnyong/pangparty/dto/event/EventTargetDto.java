package nyongnyong.pangparty.dto.event;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventTargetDto {
    private Long uid;
    @NonNull
    private Long eventUid;
    @NonNull
    private Long memberUid;
    private LocalDateTime addTime;

    @Builder
    public EventTargetDto(Long uid, Long eventUid, Long memberUid, LocalDateTime addTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberUid = memberUid;
        this.addTime = addTime;
    }
}
