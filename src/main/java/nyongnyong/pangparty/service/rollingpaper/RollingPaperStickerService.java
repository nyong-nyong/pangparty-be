package nyongnyong.pangparty.service.rollingpaper;


import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerRequestDto;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerResponseDto;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;

import java.util.List;

public interface RollingPaperStickerService {
    
    // 주어진 범위에 맞춰 스티커 검색
    List<RollingPaperStickerResponseDto> findRollingPaperStickersByTopLoc(Long rollingPaperUid, int topStart, int topEnd);

    // 스티커 등록
    Long addRollingPaperSticker(RollingPaperStickerRequestDto rollingPaperStickerRequestDto);

}
