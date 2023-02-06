package nyongnyong.pangparty.dto.event;

import lombok.Builder;

import java.time.LocalDateTime;

public class EventLikeDto {
    private Long uid;
    private Long eventUid;
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
