package nyongnyong.pangparty.dto.event;

import lombok.Builder;

import java.time.LocalDateTime;

public class EventParticipantDto {
    private Long uid;
    private Long eventUid;
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
