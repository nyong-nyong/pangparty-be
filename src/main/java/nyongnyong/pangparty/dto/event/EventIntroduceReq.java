package nyongnyong.pangparty.dto.event;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventIntroduceReq {
    private String targetId;
    @NotBlank
    private String eventName;
    @NotBlank
    private LocalDateTime dDay;
    private String introduction;
    private List<Long> hashtags;
    private String imgUrl;

    public EventIntroduceReq(String targetId, @NotBlank String eventName, @NotBlank LocalDateTime dDay, String introduction, List<Long> hashtags, String imgUrl) {
        this.targetId = targetId;
        this.eventName = eventName;
        this.dDay = dDay;
        this.introduction = introduction;
        this.hashtags = hashtags;
        this.imgUrl = imgUrl;
    }

//    @Builder
//    public static EventIntroduceRequestDto from(Event event, EventTarget eventTarget, MemberProfile memberProfile){
//        return EventIntroduceRequestDto.builder()
//                .targetId(memberProfile.getId())
//                .dDay(eventTarget.getDday())
//                .eventName(event.getEventName())
//                .isLiked(eventTarget.isLiked())
//                .introduction(event.getIntroduction())
//                .imgUrl(event.getImgUrl())
//                .hashtags(event.getHashtags())
//                .albumMedias()
//                .build();
//    }


//    @Builder
//    public ResEventIntroduceDto(Long targetId, LocalDateTime dDay, String eventName, boolean isLiked, String introduction, String imgUrl, List<Long> hashtags, List<Long> albumMedias) {
//        this.targetId = targetId;
//        this.dDay = dDay;
//        this.eventName = eventName;
//        this.isLiked = isLiked;
//        this.introduction = introduction;
//        this.imgUrl = imgUrl;
//        this.hashtags = hashtags;
//        this.albumMedias = albumMedias;
//    }
}
