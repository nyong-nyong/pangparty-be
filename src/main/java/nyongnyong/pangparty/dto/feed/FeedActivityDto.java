package nyongnyong.pangparty.dto.feed;

import lombok.Builder;

import java.time.LocalDateTime;

public class FeedActivityDto {
    private Long uid;
    private Long postUid;
    private Long memberUid;
    private LocalDateTime likeTime;

    @Builder
    public FeedActivityDto(Long uid, Long postUid, Long memberUid, LocalDateTime likeTime) {
        this.uid = uid;
        this.postUid = postUid;
        this.memberUid = memberUid;
        this.likeTime = likeTime;
    }
}
