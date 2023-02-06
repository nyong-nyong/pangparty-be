package nyongnyong.pangparty.service.rollingpaper;


import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerDto;
import nyongnyong.pangparty.dto.rollingpaper.StickerDto;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;
import nyongnyong.pangparty.entity.rollingpaper.Sticker;

import java.util.List;

public interface RollingPaperStickerService {
    
    // 주어진 범위에 맞춰 스티커 검색
    List<RollingPaperSticker> findRollingPaperStickersByTopLoc(Long eventUid, Long rollingPaperUid, int topStart, int topEnd);

    // 스티커 등록
    Long addRollingPaperSticker(RollingPaperSticker rollingPaperSticker);

}
