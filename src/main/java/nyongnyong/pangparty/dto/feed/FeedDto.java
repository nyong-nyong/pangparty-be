package nyongnyong.pangparty.dto.feed;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FeedDto {
    @Id
    private Long uid;
    @NonNull
    private Long postuid;
    @NonNull
    private Long memberUid;
    private LocalDateTime likeTime;

    @Builder
    public FeedDto(Long uid, Long postuid, Long memberUid, LocalDateTime likeTime) {
        this.uid = uid;
        this.postuid = postuid;
        this.memberUid = memberUid;
        this.likeTime = likeTime;
    }
}
