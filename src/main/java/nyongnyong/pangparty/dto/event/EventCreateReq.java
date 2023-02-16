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
    private String imgUrl = randomImgUrl((int)((Math.random()*2)+1));

    static private String randomImgUrl(int num) {
        return "/eventDefaults/eventDefaultHeader"+num+".png";
    }
}
