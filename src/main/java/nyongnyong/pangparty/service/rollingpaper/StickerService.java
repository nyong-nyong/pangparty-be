package nyongnyong.pangparty.service.rollingpaper;

import nyongnyong.pangparty.dto.rollingpaper.StickerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StickerService {

    /**
     * 스티커 리스트 조회
     *
     * @param pageable Pageable 페이지 정보: page, size 등
     * @return Page<Sticker> 스티커 리스트
     */
    Page<StickerDto> findStickerList(Pageable pageable);


    /**
     * 스티커 존재 여부
     *
     * @param stickerUid 스티커 uid
     * @return boolean 존재 여부
     */
    boolean isExistStickerByStickerUid(Long stickerUid);

}
