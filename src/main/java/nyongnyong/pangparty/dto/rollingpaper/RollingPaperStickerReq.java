package nyongnyong.pangparty.dto.rollingpaper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@ToString
public class RollingPaperStickerReq {

    @NotBlank
    private Long rollingPaperUid;
    //    private Long memberUid;
    @NotBlank
    private Long stickerUid;

    @NotBlank
    @Digits(integer = 5, fraction = 0)
    private int leftLoc;

    @NotBlank
    @Digits(integer = 5, fraction = 0)
    private int topLoc;

    @NotBlank
    @JsonProperty("zIndex")
    private String zIndex;

    @NotBlank
    private float angle;

    @NotBlank
    private float scale;

    public RollingPaperStickerReq(RollingPaperSticker rollingPaperSticker) {
        this.rollingPaperUid = rollingPaperSticker.getRollingPaper().getUid();
//        this.memberUid = rollingPaperSticker.getMember().getUid();
        this.stickerUid = rollingPaperSticker.getSticker().getUid();
        this.leftLoc = rollingPaperSticker.getLeftLoc();
        this.topLoc = rollingPaperSticker.getTopLoc();
        this.zIndex = rollingPaperSticker.getZIndex();
        this.angle = rollingPaperSticker.getAngle();
        this.scale = rollingPaperSticker.getScale();
    }
}
