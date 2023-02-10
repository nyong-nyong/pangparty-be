package nyongnyong.pangparty.dto.event;

import lombok.*;

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

    public EventCard(Long eventUid, String eventName, String targetId, String imgUrl, LocalDate dDay) {
        this.eventUid = eventUid;
        this.eventName = eventName;
        this.targetId = targetId;
        this.imgUrl = imgUrl;
        this.dDay = dDay;
    }
}
