package nyongnyong.pangparty.dto.event;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventParticipantDto {
    @Id
    private Long uid;
    @NonNull
    private Long eventUid;
    @NonNull
    private Long memberUid;
    private LocalDateTime joinTime;

    @Builder
    public EventParticipantDto(Long uid, Long eventUid, Long memberUid, LocalDateTime joinTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberUid = memberUid;
        this.joinTime = joinTime;
    }
}
