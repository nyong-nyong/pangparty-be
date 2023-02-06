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

    public RollingPaperDto(Long uid, Long eventUid, String bgColor) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.bgColor = bgColor;
    }

    public RollingPaper toEntity() {
        return RollingPaper.builder().bgColor(bgColor).build();
    }
}
