package nyongnyong.pangparty.service.rollingpaper;

import nyongnyong.pangparty.dto.rollingpaper.StickerSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StickerService {

    /**
     * 스티커 리스트 조회
     *
     * @param pageable Pageable 페이지 정보: page, size 등
     * @return Page<StickerSimple> 스티커 리스트
     */
    Page<StickerSimple> findStickerList(Pageable pageable);


    /**
     * 스티커 존재 여부
     *
     * @param stickerUid 스티커 uid
     * @return boolean 존재 여부
     */
    boolean isExistStickerByStickerUid(Long stickerUid);

}
