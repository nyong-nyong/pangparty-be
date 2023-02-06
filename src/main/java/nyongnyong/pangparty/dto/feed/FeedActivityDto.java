package nyongnyong.pangparty.dto.feed;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FeedActivityDto {
    @Id
    private Long uid;
    @NonNull
    private Long postUid;
    @NonNull
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
