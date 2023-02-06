package nyongnyong.pangparty.service.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.rollingpaper.StickerDto;
import nyongnyong.pangparty.repository.rollingpaper.StickerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

    private final StickerRepository stickerRepository;

    @Override
    public Page findStickerList(Pageable pageable) {
        return stickerRepository.findAll(pageable).map(sticker -> new StickerDto(sticker));
    }
}