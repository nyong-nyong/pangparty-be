package nyongnyong.pangparty.service.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerReq;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerRes;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperStickerRepository;
import nyongnyong.pangparty.repository.rollingpaper.StickerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RollingPaperStickerServiceImpl implements RollingPaperStickerService {

    private final StickerRepository stickerRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final RollingPaperStickerRepository rollingPaperStickerRepository;

    @Override
    @Transactional
    public List<RollingPaperStickerRes> findRollingPaperStickersByTopLoc(Long rollingPaperUid, int topStart, int topEnd) {
        return rollingPaperStickerRepository.findRollingPaperStickersByTopLoc(rollingPaperUid, topStart, topEnd).stream().map(RollingPaperStickerRes::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long addRollingPaperSticker(@Valid final RollingPaperStickerReq rollingPaperStickerReq) {
        RollingPaperSticker rollingPaperSticker = toEntity(rollingPaperStickerReq);
        rollingPaperStickerRepository.save(rollingPaperSticker);
        return rollingPaperSticker.getUid();
    }

    private RollingPaperSticker toEntity(RollingPaperStickerReq rollingPaperStickerReq) {
        return RollingPaperSticker.builder()
                .sticker(stickerRepository.findById(rollingPaperStickerReq.getStickerUid()).orElseThrow(() -> new IllegalArgumentException("해당 스티커가 존재하지 않습니다.")))
                .rollingPaper(rollingPaperRepository.findById(rollingPaperStickerReq.getRollingPaperUid()).orElseThrow(() -> new IllegalArgumentException("해당 롤링페이퍼가 존재하지 않습니다.")))
                // TODO add find Member by Access Token Info
                .topLoc(rollingPaperStickerReq.getTopLoc())
                .leftLoc(rollingPaperStickerReq.getLeftLoc())
                .zIndex(rollingPaperStickerReq.getZIndex())
                .scale(rollingPaperStickerReq.getScale()).build();
    }
}
