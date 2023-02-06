package nyongnyong.pangparty.dto.feed;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostLikeDto {
    @Id
    private Long uid;
    @NonNull
    private Long postUid;
    @NonNull
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
