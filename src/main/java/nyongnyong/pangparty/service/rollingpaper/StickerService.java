package nyongnyong.pangparty.service.rollingpaper;

import nyongnyong.pangparty.entity.rollingpaper.Sticker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface StickerService {
    // 스티커 검색
    Page<Sticker> findStickerList(Pageable pageable);
}
