package nyongnyong.pangparty.repository.rollingpaper;

import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RollingPaperStickerRepository extends JpaRepository<RollingPaperSticker, Long> {

    @Query("select s from RollingPaperSticker s left join fetch s.sticker where s.rollingPaper.uid = ?1 and s.topLoc between ?2 and ?3 order by s.topLoc asc")
    List<RollingPaperSticker> findRollingPaperStickersByTopLoc(Long rollingPaperUid, int topStart, int topEnd);

}
