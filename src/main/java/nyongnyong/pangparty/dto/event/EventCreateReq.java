package nyongnyong.pangparty.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Builder
@AllArgsConstructor
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

    @JsonProperty("hashtags")
    private List<SimpleHashtagName> hashtags;
    @Builder.Default
    private String imgUrl = ramdomImgUrl((int)((Math.random()*2)+1));

    public EventCreateReq(Long eventUid, Long hostUid, String targetId, String eventName, String introduction, LocalDate dDay, boolean isPrivate, List<SimpleHashtagName> hashtags) {
        this.eventUid = eventUid;
        this.hostUid = hostUid;
        this.targetId = targetId;
        this.eventName = eventName;
        this.introduction = introduction;
        this.dDay = dDay;
        this.isPrivate = isPrivate;
        this.hashtags = hashtags;
        this.imgUrl = ramdomImgUrl((int)((Math.random()*2)+1));
    }

    private String ramdomImgUrl(int num) {
        return "/eventDefaults/eventDefaultHeader"+num+".png";
    }
}
