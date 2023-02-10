package nyongnyong.pangparty.service.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperPieceReq;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperPieceRes;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperPiece;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperPieceRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RollingPaperPieceServiceImpl implements RollingPaperPieceService {

    private final RollingPaperRepository rollingPaperRepository;
    private final RollingPaperPieceRepository rollingPaperPieceRepository;

    @Override
    @Transactional
    public Page<RollingPaperPieceRes> findRollingPaperPieces(Long rollingPaperUid, Pageable pageable) {
        return rollingPaperPieceRepository.findAllByRollingPaperUid(rollingPaperUid, pageable).map(RollingPaperPieceRes::new);
    }

    @Override
    @Transactional
    public Long addRollingPaperPiece(RollingPaperPieceReq rollingPaperPieceReq) {
        RollingPaperPiece rollingPaperPiece = toEntity(rollingPaperPieceReq);
        rollingPaperPieceRepository.save(rollingPaperPiece);
        return rollingPaperPiece.getUid();
    }

    RollingPaperPiece toEntity(RollingPaperPieceReq rollingPaperPieceReq) {
        return RollingPaperPiece.builder().rollingPaper(rollingPaperRepository.findById(rollingPaperPieceReq.getRollingPaperUid()).orElseThrow(() -> new IllegalArgumentException("해당 롤링페이퍼가 존재하지 않습니다.")))
                // TODO Member를 Id로 찾아서 넣어주기
                .content(rollingPaperPieceReq.getContent())
                .writerName(rollingPaperPieceReq.getWriterName())
                .content(rollingPaperPieceReq.getContent())
                .bgColor(rollingPaperPieceReq.getBgColor())
                .fontFamily(rollingPaperPieceReq.getFontFamily())
                .textColor(rollingPaperPieceReq.getTextColor())
                .textAlign(rollingPaperPieceReq.getTextAlign())
                .build();
    }
}
