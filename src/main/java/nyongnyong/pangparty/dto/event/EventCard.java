package nyongnyong.pangparty.dto.event;

import lombok.*;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventTarget;
import nyongnyong.pangparty.entity.member.MemberProfile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public EventCard(Long eventUid, String eventName, String targetId, String imgUrl, LocalDate dDay) {
        this.eventUid = eventUid;
        this.eventName = eventName;
        this.targetId = targetId;
        this.imgUrl = imgUrl;
        this.dDay = dDay;
    }
}
