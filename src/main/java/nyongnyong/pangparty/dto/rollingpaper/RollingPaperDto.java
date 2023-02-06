package nyongnyong.pangparty.dto.rollingpaper;

import lombok.Data;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;

@Data
@NoArgsConstructor
public class RollingPaperDto {

    private Long uid;
    private Long eventUid;
    private String bgColor;

    public RollingPaperDto(RollingPaper rollingPaper) {
        this.uid = rollingPaper.getUid();
        this.eventUid = rollingPaper.getEvent().getUid();
        this.bgColor = rollingPaper.getBgColor();
    }

    public RollingPaper toEntity() {
        return RollingPaper.builder().bgColor(bgColor).build();
    }
}
