package nyongnyong.pangparty.dto.feed;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostCommentReq {

    @NotBlank
    private String content;

    @Builder
    public PostCommentReq(String content) {
        this.content = content;
    }
}
