package nyongnyong.pangparty.dto.hashtag;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberHashtagDto {
    private Long uid;
    private Long hashtagUid;
    private Long memberUid;
    private LocalDateTime addTime;

    @Builder
    public MemberHashtagDto(Long uid, Long hashtagUid, Long memberUid, LocalDateTime addTime) {
        this.uid = uid;
        this.hashtagUid = hashtagUid;
        this.memberUid = memberUid;
        this.addTime = addTime;
    }
}
