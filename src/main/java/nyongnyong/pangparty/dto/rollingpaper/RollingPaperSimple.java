package nyongnyong.pangparty.dto.rollingpaper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RollingPaperSimple {

    private Long uid;
    private Long eventUid;
    private String bgColor;

    public RollingPaperSimple(nyongnyong.pangparty.entity.rollingpaper.RollingPaper rollingPaper) {
        this.uid = rollingPaper.getUid();
        this.eventUid = rollingPaper.getEvent().getUid();
        this.bgColor = rollingPaper.getBgColor();
    }
}
