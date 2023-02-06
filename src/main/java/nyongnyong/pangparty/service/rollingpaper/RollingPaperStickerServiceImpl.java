package nyongnyong.pangparty.service.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperStickerRepository;
import nyongnyong.pangparty.repository.rollingpaper.StickerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RollingPaperStickerServiceImpl implements RollingPaperStickerService {

    private final EventRepository eventRepository;
    private final StickerRepository stickerRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final RollingPaperStickerRepository rollingPaperStickerRepository;


    @Override
    @Transactional
    public List<RollingPaperSticker> findRollingPaperStickersByTopLoc(Long eventUid, Long rollingPaperUid, int topStart, int topEnd) {
        List<RollingPaperSticker> rollingPaperStickers = rollingPaperStickerRepository.findRollingPaperStickersByTopLoc(topStart, topEnd);
        return rollingPaperStickers;
    }

    @Override
    @Transactional
    public Long addRollingPaperSticker(RollingPaperSticker rollingPaperSticker) {
        rollingPaperStickerRepository.save(rollingPaperSticker);
        return rollingPaperSticker.getUid();
    }

}
