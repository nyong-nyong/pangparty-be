package nyongnyong.pangparty.dto.rollingpaper;

import lombok.Data;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperPiece;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RollingPaperPieceDto {
    private Long uid;
    private Long rollingPaperUid;
    private String writerName;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String content;
    private String bgColor;
    private String bgImgUrl;
    private String bgImgAlt;
    private String fontFamily;
    private String textColor;
    private String textAlign;

    public RollingPaperPieceDto(RollingPaperPiece rollingPaperPiece) {
        this.uid = rollingPaperPiece.getUid();
        this.rollingPaperUid = rollingPaperPiece.getRollingPaper().getUid();
        this.writerName = rollingPaperPiece.getWriterName();
        this.createTime = rollingPaperPiece.getCreateTime();
        this.modifyTime = rollingPaperPiece.getModifyTime();
        this.content = rollingPaperPiece.getContent();
        this.bgColor = rollingPaperPiece.getBgColor();
        this.bgImgUrl = rollingPaperPiece.getBgImgUrl();
        this.bgImgAlt = rollingPaperPiece.getBgImgAlt();
        this.fontFamily = rollingPaperPiece.getFontFamily();
        this.textColor = rollingPaperPiece.getTextColor();
        this.textAlign = rollingPaperPiece.getTextAlign();
    }

    public RollingPaperPiece toEntity() {
        return RollingPaperPiece.builder().writerName(writerName).content(content).bgColor(bgColor).bgImgUrl(bgImgUrl).bgImgAlt(bgImgAlt).fontFamily(fontFamily).textColor(textColor).textAlign(textAlign).build();
    }

}
