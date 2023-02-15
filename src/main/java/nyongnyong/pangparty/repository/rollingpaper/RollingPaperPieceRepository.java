package nyongnyong.pangparty.repository.rollingpaper;

import nyongnyong.pangparty.entity.rollingpaper.RollingPaperPiece;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RollingPaperPieceRepository extends JpaRepository<RollingPaperPiece, Long> {
    Page<RollingPaperPiece> findAllByRollingPaperUid(Long rollingPaperUid, Pageable pageable);
    List<RollingPaperPiece> findAllByRollingPaperUid(Long rollingPaperUid);
    int countByMemberUidAndRollingPaperUid(Long memberUid, Long rollingPaperUid);
}
