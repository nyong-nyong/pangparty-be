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
    Page<RollingPaperPieceRes> findRollingPaperPieces(Long eventUid, Long rollingPaperUid, Pageable pageable);

    /**
     * 롤링페이퍼 조각 추가
     *
     * @param rollingPaperPieceReq 롤링페이퍼 조각 정보
     * @return 롤링페이퍼 조각 uid
     */
    Long addRollingPaperPiece(Long memberUid, Long eventUid, RollingPaperPieceReq rollingPaperPieceReq);

    /**
     * 롤링페이퍼 조각 추가 (비로그인)
     *
     * @param rollingPaperPieceReq 롤링페이퍼 조각 정보
     * @return 롤링페이퍼 조각 uid
     */
    Long addRollingPaperPiece(Long eventUid, RollingPaperPieceReq rollingPaperPieceReq);

    /**
     * 롤링페이퍼 조각 삭제
     *
     * @param rollingPaperPieceUid 롤링페이퍼 조각 uid
     */
//    void removeRollingPaperPiece(Long memberUid, Long eventUid, Long rollingPaperPieceUid);

    /**
     * 롤링페이퍼 조각 uid로 존재 여부 확인
     * @param rollingPaperPieceUid 롤링페이퍼 조각 uid
     * @return 존재 여부
     */
    boolean isExistByRollingPaperPieceUid(Long rollingPaperPieceUid);
}
