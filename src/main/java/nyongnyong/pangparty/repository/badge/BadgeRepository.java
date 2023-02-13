package nyongnyong.pangparty.repository.badge;

import nyongnyong.pangparty.dto.badge.MemberBadgeRes;
import nyongnyong.pangparty.entity.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    @Query("select new nyongnyong.pangparty.dto.badge.MemberBadgeRes" +
            "(b.uid, b.badgeName, b.trueImgUrl, b.falseImgUrl, b.badgeCondition, mb.acquireTime) " +
            "from Badge b left join MemberBadge mb on b.uid = mb.badge.uid " +
            "where mb.member.uid = :memberUid")
    List<MemberBadgeRes> getBadgeListAsMemberBadgeRes(Long memberUid);
}
