package nyongnyong.pangparty.repository.rollingpaper;

import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RollingPaperRepository extends JpaRepository<RollingPaper, Long> {

    @Query("select r from RollingPaper r left join fetch r.event where r.event.uid = ?1")
    RollingPaper findRollingPaperByEventUid(Long eventUid);
}
