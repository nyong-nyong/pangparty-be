package nyongnyong.pangparty.dto.feed;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostDto {
    @Id
    private Long uid;
    @NonNull
    private Long eventUid;
    @NonNull
    private Long memberUid;

    @UniqueElements
    private List<Long> postCommentUid;
    @UniqueElements
    private List<Long> postLikeUid;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private int hit;

    @Builder
    public PostDto(Long uid, Long eventUid, Long memberUid, List<Long> postCommentUid, List<Long> postLikeUid, String content, LocalDateTime createTime, LocalDateTime modifyTime, int hit) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberUid = memberUid;
        this.postCommentUid = postCommentUid;
        this.postLikeUid = postLikeUid;
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.hit = hit;
    }
}
