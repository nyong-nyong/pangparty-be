package nyongnyong.pangparty.repository.badge;

import nyongnyong.pangparty.dto.badge.BadgeRes;
import nyongnyong.pangparty.entity.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    @Query("select new nyongnyong.pangparty.dto.badge.BadgeRes(b.uid, b.badgeName, b.trueImgUrl, b.falseImgUrl, b.badgeCondition) from Badge b")
    List<BadgeRes> getBadgeList();
}
