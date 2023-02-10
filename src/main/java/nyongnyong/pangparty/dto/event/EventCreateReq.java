package nyongnyong.pangparty.dto.event;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventCreateReq {
    @NotBlank
    private Long eventUid;
    private Long hostUid;
    private String targetId;    // targetId -> targetMemberUid -> 이벤트 주인공 목록 테이블 채우기
    @NotBlank
    private String eventName;
    private String introduction;
    @NotBlank
    private LocalDate dDay;
    private boolean isPrivate;
    private List<Long> hashtags;
    private String imgUrl;

    @Builder
    public EventCreateReq(Long eventUid, Long hostUid, String targetId, String eventName, String introduction, LocalDate dDay, boolean isPrivate, List<Long> hashtags, String imgUrl) {
        this.eventUid = eventUid;
        this.hostUid = hostUid;
        this.targetId = targetId;
        this.eventName = eventName;
        this.introduction = introduction;
        this.dDay = dDay;
        this.isPrivate = isPrivate;
        this.hashtags = hashtags;
        this.imgUrl = imgUrl;
    }
}
