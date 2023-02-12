package nyongnyong.pangparty.dto.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventExportRes {
    private Long eventLikeCnt;
    private Long rollingPaperParticipantCnt;
    private Long rollingPaperCnt;
    private Long albumMediaCnt;

    @Builder
    public EventExportRes(Long eventLikeCnt, Long rollingPaperParticipantCnt, Long rollingPaperCnt, Long albumMediaCnt) {
        this.eventLikeCnt = eventLikeCnt;
        this.rollingPaperParticipantCnt = rollingPaperParticipantCnt;
        this.rollingPaperCnt = rollingPaperCnt;
        this.albumMediaCnt = albumMediaCnt;
    }
}
