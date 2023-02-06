package nyongnyong.pangparty.repository.rollingpaper;

import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RollingPaperStickerRepository extends JpaRepository<RollingPaperSticker, Long> {

    @Query("select s from RollingPaperSticker s left join fetch s.sticker where top_loc between ?1 and ?2 order by s.topLoc asc")
    List<RollingPaperSticker> findRollingPaperStickersByTopLoc(int topStart, int topEnd);

}
