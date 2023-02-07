package nyongnyong.pangparty.dto.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class EventIntroduceRes {
    private String targetId;    // eventUid -> 이벤트 주인공 목록 테이블(event_target) -> 사용자(member) -> 사용자 프로필(member_profile) -> 사용자 아이디(member_profile.id)
    private LocalDateTime dDay;
    private String eventName;
    private boolean isLiked;    // eventUid -> 이벤트 좋아요 테이블(event_like)
    private String introduction;
    private String imgUrl;
    private List<Long> hashtags;    // eventUid -> 이벤트 해시태그 테이블(event_hashtag) -> 해시태그(hashtag) -> 해시태그명(hashtag.name)
    private List<Long> albumMedias; // eventUid -> 이벤트 앨범 테이블(album) -> 앨범 미디어(album_media) -> 미디어URL(album_media.media_url)

    public EventIntroduceRes(EventIntroduceRes eventIntroduceRes) {
        this.targetId = eventIntroduceRes.getTargetId();
        this.dDay = eventIntroduceRes.getDDay();
        this.eventName = eventIntroduceRes.getEventName();
        this.isLiked = eventIntroduceRes.isLiked();
        this.introduction = eventIntroduceRes.getIntroduction();
        this.imgUrl = eventIntroduceRes.getImgUrl();
        this.hashtags = eventIntroduceRes.getHashtags();
        this.albumMedias = eventIntroduceRes.getAlbumMedias();
    }
}