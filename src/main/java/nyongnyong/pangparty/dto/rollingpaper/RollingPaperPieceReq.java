package nyongnyong.pangparty.dto.rollingpaper;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@ToString
public class RollingPaperPieceReq {
    private Long rollingPaperUid;
    // TODO add memberId
    private String memberId;
    @NotBlank
    private String writerName;
    @NotBlank
    private String content;
    private String bgColor;
    private String fontFamily;
    private String textColor;
    private String textAlign;
}
