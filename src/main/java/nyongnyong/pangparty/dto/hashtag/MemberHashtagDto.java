package nyongnyong.pangparty.dto.hashtag;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberHashtagDto {
    @Id
    private Long uid;
    @NonNull
    private Long hashtagUid;
    @NonNull
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
