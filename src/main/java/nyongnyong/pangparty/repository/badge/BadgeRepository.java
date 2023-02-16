package nyongnyong.pangparty.repository.badge;

import io.lettuce.core.dynamic.annotation.Param;
import nyongnyong.pangparty.entity.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    @Query("select b from Badge b where b.uid = :badgeUid")
    Badge findBadgeByUid(@Param("badgeUid") Long badgeUid);
}
