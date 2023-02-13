package nyongnyong.pangparty.dto.event;

import lombok.*;
import nyongnyong.pangparty.entity.event.Event;

import java.time.LocalDate;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventCard {
    private Long eventUid;
    private String eventName;
    private String targetId;
    private String imgUrl;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)   // LocalDateTime 변환 과정에서 오류나기도 하나봄..?
    private LocalDate dDay;

    @Builder
    public EventCard(Long eventUid, String eventName, String targetId, String imgUrl, LocalDate dDay) {
        this.eventUid = eventUid;
        this.eventName = eventName;
        this.targetId = targetId;
        this.imgUrl = imgUrl;
        this.dDay = dDay;
    }

    public EventCard(Event event) {
        this.eventUid = event.getUid();
        this.eventName = event.getEventName();
        this.targetId = event.getEventTarget().getTargetMember().getMemberProfile().getId();
        this.imgUrl = event.getImgUrl();
        this.dDay = event.getDDay();
    }

}
