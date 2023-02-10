package nyongnyong.pangparty.service.rollingpaper;

import nyongnyong.pangparty.dto.rollingpaper.RollingPaperPieceReq;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperPieceRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RollingPaperPieceService {
    /**
     * 롤링페이퍼 조각 목록 조회
     *
     * @param rollingPaperUid 롤링페이퍼 uid
     * @param pageable        페이징 정보
     * @return Page<RollingPaperPieceRes> 롤링페이퍼 조각 목록
     */
    Page<RollingPaperPieceRes> findRollingPaperPieces(Long rollingPaperUid, Pageable pageable);

    /**
     * 롤링페이퍼 조각 추가
     *
     * @param rollingPaperPieceReq 롤링페이퍼 조각 정보
     * @return 롤링페이퍼 조각 uid
     */
    Long addRollingPaperPiece(RollingPaperPieceReq rollingPaperPieceReq);

}
