package nyongnyong.pangparty.dto.event;

import lombok.*;
import nyongnyong.pangparty.entity.event.Event;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventDto {
    @Id
    private Long uid;
    @NonNull
    private Long hostUid;
    private String eventName;
    private String introduction;
    private String imgUrl;
    private LocalDate dDay;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime partyTime;
    private boolean isPrivate;



    public Event toEntity() {
        return Event.builder().eventName(eventName).introduction(introduction).imgUrl(imgUrl).dDay(dDay)
                .createTime(createTime).modifyTime(modifyTime).startTime(startTime).endTime(endTime).partyTime(partyTime).isPrivate(isPrivate).build();
    }

//    @Builder
    public EventDto(Long uid, Long hostUid, String eventName, String introduction, String imgUrl, LocalDate dDay, LocalDateTime createTime, LocalDateTime modifyTime, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime partyTime, boolean isPrivate) {
        this.uid = uid;
        this.hostUid = hostUid;
        this.eventName = eventName;
        this.introduction = introduction;
        this.imgUrl = imgUrl;
        this.dDay = dDay;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.partyTime = partyTime;
        this.isPrivate = isPrivate;
    }
}
