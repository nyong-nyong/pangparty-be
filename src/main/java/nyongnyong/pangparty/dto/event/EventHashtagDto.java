package nyongnyong.pangparty.dto.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "addTime"})
public class EventHashtagDto {
    private Long uid;
    private Long eventUid;
    private Long hashtagUid;
    private LocalDateTime addTime;

    @Builder
    public EventHashtagDto(Long uid, Long eventUid, Long hashtagUid, LocalDateTime addTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.hashtagUid = hashtagUid;
        this.addTime = addTime;
    }
}
