package nyongnyong.pangparty.dto.feed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FeedDto {
    @NotNull
    private Long uid;
    private Long eventUid;
    @NotBlank
    private String memberId;
    @JsonProperty("isLiked")
    private boolean isLiked;
    private String title;
    private String content;
    private String imgUrl;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
//    private int hit;

    @Builder
    public FeedDto(Long uid, Long eventUid, String memberId, boolean isLiked, String title, String content, String imgUrl, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberId = memberId;
        this.isLiked = isLiked;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
//        this.hit = hit;
    }
}
