package nyongnyong.pangparty.dto.rollingpaper;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperPiece;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@ToString
public class RollingPaperPieceRes {
    //    private Long uid;
    @NotBlank
    private Long rollingPaperUid;
    // TODO add memberId
    private String memberId;
    @NotBlank
    private String writerName;
    //    private LocalDateTime createTime;
//    private LocalDateTime modifyTime;
    @NotBlank
    private String content;
    private String bgColor;
//    private String bgImgUrl;
//    private String bgImgAlt;
    private String fontFamily;
    private String textColor;
    private String textAlign;

    public RollingPaperPieceRes(RollingPaperPiece rollingPaperPiece) {
//        this.uid = rollingPaperPiece.getUid();
        this.rollingPaperUid = rollingPaperPiece.getRollingPaper().getUid();
//        this.memberId = rollingPaperPiece.getMemberProfile().getId();
        this.writerName = rollingPaperPiece.getWriterName();
//        this.createTime = rollingPaperPiece.getCreateTime();
//        this.modifyTime = rollingPaperPiece.getModifyTime();
        this.content = rollingPaperPiece.getContent();
        this.bgColor = rollingPaperPiece.getBgColor();
//        this.bgImgUrl = rollingPaperPiece.getBgImgUrl();
//        this.bgImgAlt = rollingPaperPiece.getBgImgAlt();
        this.fontFamily = rollingPaperPiece.getFontFamily();
        this.textColor = rollingPaperPiece.getTextColor();
        this.textAlign = rollingPaperPiece.getTextAlign();
    }
}
