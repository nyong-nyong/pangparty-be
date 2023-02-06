package nyongnyong.pangparty.dto.event;

import lombok.Builder;

import java.time.LocalDateTime;

public class EventTargetDto {
    private Long uid;
    private Long eventUid;
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
