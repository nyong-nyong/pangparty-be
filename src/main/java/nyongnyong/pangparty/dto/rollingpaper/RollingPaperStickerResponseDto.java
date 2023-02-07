package nyongnyong.pangparty.dto.rollingpaper;

import lombok.Data;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RollingPaperStickerResponseDto {

    //    private Long uid;
    //    private Long rollingPaperUid;
    //    private Long stickerUid;
    @NotBlank
    private String stickerUrl;

    //    private LocalDateTime createTime;
    @NotBlank
    @Digits(integer = 5, fraction = 0)
    private int leftLoc;

    @NotBlank
    @Digits(integer = 5, fraction = 0)
    private int topLoc;

    @NotBlank
    private String zIndex;

    @NotBlank
    private float angle;

    @NotBlank
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
