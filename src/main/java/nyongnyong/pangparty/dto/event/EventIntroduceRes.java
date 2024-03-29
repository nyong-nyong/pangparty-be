package nyongnyong.pangparty.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class EventIntroduceRes {
    private String targetId;    // eventUid -> 이벤트 주인공 목록 테이블(event_target) -> 사용자(member) -> 사용자 프로필(member_profile) -> 사용자 아이디(member_profile.id)
    private LocalDate dDay;
    private String eventName;

    @JsonProperty("isLiked")
    private boolean isLiked;    // eventUid -> 이벤트 좋아요 테이블(event_like)
    private String introduction;
    private String imgUrl;

    private Long likeCnt;

    private Long rollingPaperCnt;    // TODO: rollingPaper 쪽에서 가져와야 함
    private Long rollingPaperUid;

    @JsonProperty("hashtags")
    private List<SimpleHashtagName> hashtags;    // eventUid -> 이벤트 해시태그 테이블(event_hashtag) -> 해시태그(hashtag) -> 해시태그명(hashtag.name)
//    private List<String> albumMediaUrls; // eventUid -> 이벤트 앨범 테이블(album) -> 앨범 미디어(album_media) -> 미디어URL(album_media.media_url) // 근데 이렇게하면 "name": "url1", "name": "url2", 이런식이 아니라 ["url1", "url2"] 이렇게 주게됨

    @Builder
    public EventIntroduceRes(String targetId, LocalDate dDay, String eventName, boolean isLiked,
                             String introduction, String imgUrl, List<SimpleHashtagName> hashtags, Long likeCnt, Long rollingPaperCnt, Long rollingPaperUid) {
        this.targetId = targetId;
        this.dDay = dDay;
        this.eventName = eventName;
        this.isLiked = isLiked;
        this.introduction = introduction;
        this.imgUrl = imgUrl;
        this.hashtags = hashtags;
        this.likeCnt = likeCnt;
        this.rollingPaperCnt = rollingPaperCnt;
        this.rollingPaperUid = rollingPaperUid;
//        this.albumMediaUrls = albumMediaUrls;
    }

    public EventIntroduceRes(String targetId, LocalDate dDay, String eventName, boolean isLiked,
                             String introduction, String imgUrl, List<SimpleHashtagName> hashtags, Long likeCnt) {
        this.targetId = targetId;
        this.dDay = dDay;
        this.eventName = eventName;
        this.isLiked = isLiked;
        this.introduction = introduction;
        this.imgUrl = imgUrl;
        this.hashtags = hashtags;
        this.likeCnt = likeCnt;
//        this.albumMediaUrls = albumMediaUrls;
    }
}

