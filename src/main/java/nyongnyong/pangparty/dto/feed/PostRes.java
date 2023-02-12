package nyongnyong.pangparty.dto.feed;

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

    @UniqueElements
    private List<PostCommentRes> postComments;

    private Long likeCount;
    private boolean isLiked;
    private String title;
    private String content;
    private String imgUrl;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
//    private int hit;

    @Builder
    public PostRes(Long uid, Long eventUid, String memberId, List<PostCommentRes> postComments, Long likeCount, boolean isLiked, String content, String imgUrl, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberId = memberId;
        this.postComments = postComments;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.content = content;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
//        this.hit = hit;
    }

    public PostRes(Long uid, Long eventUid, String memberId, String title, String content, String imgUrl, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
//        this.hit = hit;
    }
}
