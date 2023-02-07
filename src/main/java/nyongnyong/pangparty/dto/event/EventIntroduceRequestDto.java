package nyongnyong.pangparty.dto.event;

import lombok.*;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventTarget;
import nyongnyong.pangparty.entity.member.MemberProfile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventIntroduceRequestDto {
    private String targetId;
    private LocalDateTime dDay;
    private String eventName;
    private boolean isLiked;
    private String introduction;
    private String imgUrl;

    private List<Long> hashtags;
    private List<Long> albumMedias;



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
