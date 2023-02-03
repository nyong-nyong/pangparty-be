package nyongnyong.pangparty.dto.event;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "email", "isSocial", "withdrawTime"})
public class EventDto {
    private Long uid;
    private Long hostUid;
    private String eventName;
    private String introduction;
    private String imgUrl;
    private LocalDate dDay;
    private String createTime;
    private String modifyTime;
    private String startTime;
    private String endTime;
    private String partyTime;
    private boolean isPrivate;

    @Builder
    public EventDto(Long uid, Long hostUid, String eventName, String introduction, String imgUrl, LocalDate dDay, String createTime, String modifyTime, String startTime, String endTime, String partyTime, boolean isPrivate) {
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
