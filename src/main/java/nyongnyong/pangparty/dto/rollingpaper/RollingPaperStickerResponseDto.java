package nyongnyong.pangparty.dto.rollingpaper;

import lombok.Data;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;

@Data
@NoArgsConstructor
public class RollingPaperStickerResponseDto {

//    private Long uid;
//    private Long rollingPaperUid;
//    private Long stickerUid;
    private String stickerUrl;
//    private LocalDateTime createTime;
    private int leftLoc;
    private int topLoc;
    private String zIndex;
    private float angle;
    private float scale;

    public RollingPaperStickerResponseDto(RollingPaperSticker rollingPaperSticker) {
//        this.uid = rollingPaperSticker.getUid();
//        this.rollingPaperUid = rollingPaperSticker.getRollingPaper().getUid();
//        this.stickerUid = rollingPaperSticker.getSticker().getUid();
//        this.createTime = rollingPaperSticker.getCreateTime();
        this.stickerUrl = rollingPaperSticker.getSticker().getStickerUrl();
        this.leftLoc = rollingPaperSticker.getLeftLoc();
        this.topLoc = rollingPaperSticker.getTopLoc();
        this.zIndex = rollingPaperSticker.getZIndex();
        this.angle = rollingPaperSticker.getAngle();
        this.scale = rollingPaperSticker.getScale();
    }
}
