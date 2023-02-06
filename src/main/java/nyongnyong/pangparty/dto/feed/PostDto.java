package nyongnyong.pangparty.dto.feed;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    private Long uid;
    private Long eventUid;
    private Long memberUid;
    private List<Long> postCommentUid;
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
