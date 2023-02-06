package nyongnyong.pangparty.dto.feed;

import lombok.Builder;

import java.time.LocalDateTime;

public class PostLikeDto {
    private Long uid;
    private Long postUid;
    private Long memberUid;
    private String content;
    private LocalDateTime createTime;

    @Builder
    public PostLikeDto(Long uid, Long postUid, Long memberUid, String content, LocalDateTime createTime) {
        this.uid = uid;
        this.postUid = postUid;
        this.memberUid = memberUid;
        this.content = content;
        this.createTime = createTime;
    }
}
