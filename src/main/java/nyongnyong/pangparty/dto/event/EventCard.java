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
    private List<String> targetIds;
    private String imgUrl;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)   // LocalDateTime 변환 과정에서 오류나기도 하나봄..?
    private LocalDate dDay;

    public EventCard(Event event){
        this.eventUid = event.getUid();
        this.eventName = event.getEventName();
        for(int i = 0; i < event.getEventTarget().size(); i++){
            this.targetIds.add(event.getEventTarget().get(i).getTargetMember().getMemberProfile().getId());
        }
        this.imgUrl = event.getImgUrl();
        this.dDay = event.getDDay();
    }
}
