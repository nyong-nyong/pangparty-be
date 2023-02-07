package nyongnyong.pangparty.service.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerRequestDto;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerResponseDto;
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
    public List<RollingPaperStickerResponseDto> findRollingPaperStickersByTopLoc(Long rollingPaperUid, int topStart, int topEnd) {
        return rollingPaperStickerRepository.findRollingPaperStickersByTopLoc(rollingPaperUid, topStart, topEnd).stream().map(RollingPaperStickerResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long addRollingPaperSticker(@Valid final RollingPaperStickerRequestDto rollingPaperStickerRequestDto) {
        RollingPaperSticker rollingPaperSticker = toEntity(rollingPaperStickerRequestDto);
        rollingPaperStickerRepository.save(rollingPaperSticker);
        return rollingPaperSticker.getUid();
    }

    private RollingPaperSticker toEntity(RollingPaperStickerRequestDto rollingPaperStickerRequestDto) {
        return RollingPaperSticker.builder()
                .sticker(stickerRepository.findById(rollingPaperStickerRequestDto.getStickerUid()).orElseThrow(() -> new IllegalArgumentException("해당 스티커가 존재하지 않습니다.")))
                .rollingPaper(rollingPaperRepository.findById(rollingPaperStickerRequestDto.getRollingPaperUid()).orElseThrow(() -> new IllegalArgumentException("해당 롤링페이퍼가 존재하지 않습니다.")))
                // TODO add find Member by Access Token Info
                .topLoc(rollingPaperStickerRequestDto.getTopLoc())
                .leftLoc(rollingPaperStickerRequestDto.getLeftLoc())
                .zIndex(rollingPaperStickerRequestDto.getZIndex())
                .scale(rollingPaperStickerRequestDto.getScale()).build();
    }
}
