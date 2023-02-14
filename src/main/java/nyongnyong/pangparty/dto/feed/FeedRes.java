package nyongnyong.pangparty.dto.feed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import nyongnyong.pangparty.dto.event.EventCard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FeedRes {
    @NotNull
    private Long uid;
    @JsonProperty("event")
    private EventCard eventCard;
    @NotBlank
    private String memberId;
    @JsonProperty("isLiked")
    private boolean isLiked;
    private Long likeCnt;
    @JsonProperty("hasCommented")
    private boolean hasCommented;
    private Long commentCnt;

    private String title;
    private String content;
    private String imgUrl;
    private String profileImgUrl;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
//    private int hit;

    @Builder
    public FeedRes(Long uid, EventCard eventCard, String memberId, boolean isLiked, Long likeCnt, boolean hasCommented, Long commentCnt, String title, String content, String imgUrl, String profileImgUrl, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.uid = uid;
        this.eventCard = eventCard;
        this.title = title;
        this.memberId = memberId;
        this.isLiked = isLiked;
        this.likeCnt = likeCnt;
        this.hasCommented = hasCommented;
        this.commentCnt = commentCnt;
        this.content = content;
        this.imgUrl = imgUrl;
        this.profileImgUrl = profileImgUrl;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
//        this.hit = hit;
    }
}
