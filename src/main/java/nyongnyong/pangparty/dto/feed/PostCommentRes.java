package nyongnyong.pangparty.dto.feed;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostCommentRes {
    @Id
    private Long uid;
    @NotBlank
    private String memberId;
    @NotBlank
    private String content;
    private LocalDateTime createTime;

    @Builder
    public PostCommentRes(Long uid, String memberId, String content, LocalDateTime createTime) {
        this.uid = uid;
        this.memberId = memberId;
        this.content = content;
        this.createTime = createTime;
    }
}
