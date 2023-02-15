package nyongnyong.pangparty.dto.feed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostRes {
    @NotNull
    private Long uid;
    private Long eventUid;
    @NotBlank
    private String memberId;
    private String profileImgUrl;
    @UniqueElements
    private List<PostCommentRes> postComments;

    private Long likeCount;
    @JsonProperty("isLiked")
    private boolean isLiked;
    private String title;
    private String content;
    private String imgUrl;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    private Long commentCount;
    @JsonProperty("hasCommented")
    private boolean hasCommented;
    //    private int hit;

    @Builder
    public PostRes(Long uid, Long eventUid, String memberId, String profileImgUrl, List<PostCommentRes> postComments, Long likeCount, boolean isLiked, String title, String content, String imgUrl, LocalDateTime createTime, LocalDateTime modifyTime, Long commentCount, boolean hasCommented) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberId = memberId;
        this.profileImgUrl = profileImgUrl;
        this.postComments = postComments;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.commentCount = commentCount;
        this.hasCommented = hasCommented;
    }

    public PostRes(Long uid, Long eventUid, String memberId, String profileImgUrl, List<PostCommentRes> postComments, Long likeCount, boolean isLiked, String content, String imgUrl, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberId = memberId;
        this.profileImgUrl = profileImgUrl;
        this.postComments = postComments;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.content = content;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
//        this.hit = hit;
    }

    public PostRes(Long uid, Long eventUid, String memberId, String profileImgUrl, String title, String content, String imgUrl, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberId = memberId;
        this.profileImgUrl = profileImgUrl;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
//        this.hit = hit;
    }
}
