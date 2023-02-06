package nyongnyong.pangparty.repository.rollingpaper;

import nyongnyong.pangparty.entity.rollingpaper.Sticker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StickerRepository extends JpaRepository<Sticker, Long> {

    @Query("select s from Sticker s order by s.uid desc")
    Page<Sticker> findPage(Pageable pageable);
}