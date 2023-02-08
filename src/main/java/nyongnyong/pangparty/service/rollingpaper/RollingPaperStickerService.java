package nyongnyong.pangparty.service.rollingpaper;


import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerReq;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerRes;

import java.util.List;

public interface RollingPaperStickerService {

    /**
     * 주어진 범위에 맞춰 스티커 검색
     *
     * @param rollingPaperUid 롤링페이퍼 uid
     * @param topStart top 시작 위치
     * @param topEnd top 끝 위치
     * @return List<RollingPaperStickerRes>
     */
    List<RollingPaperStickerRes> findRollingPaperStickersByTopLoc(Long rollingPaperUid, int topStart, int topEnd);

    /**
     * 스티커 생성
     *
     * @param rollingPaperStickerReq 롤링페이퍼 스티커 요청 dto
     * @return Long 생성된 롤링페이퍼 스티커 uid
     */
    Long addRollingPaperSticker(RollingPaperStickerReq rollingPaperStickerReq);

}
