package nyongnyong.pangparty.repository.badge;

import io.lettuce.core.dynamic.annotation.Param;
import nyongnyong.pangparty.dto.badge.MemberBadgeRes;
import nyongnyong.pangparty.entity.badge.MemberBadgeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberBadgeInfoRepository extends JpaRepository<MemberBadgeInfo, Long> {

    @Query("select mb from MemberBadgeInfo mb where mb.member.uid = :memberUid")
    MemberBadgeInfo findByMemberUid(@Param("memberUid") Long memberUid);

    @Modifying
    @Query("update MemberBadgeInfo mb set mb.loginCount = mb.loginCount + 1 where mb.member.uid = :uid")
    void updateLoginCount(@Param("uid") Long uid);

    @Modifying
    @Query("update MemberBadgeInfo mb set mb.receiveCount = mb.receiveCount + 1 where mb.member.uid = :uid")
    void updateReceiveCount(@Param("uid") Long uid);

    @Modifying
    @Query("update MemberBadgeInfo mb set mb.participateCount = mb.participateCount + 1 where mb.member.uid = :uid")
    void updateParticipateCount(@Param("uid") Long uid);

    @Query("SELECT new nyongnyong.pangparty.dto.badge.MemberBadgeRes(b.uid, b.badgeName, b.trueImgUrl, b.falseImgUrl, b.badgeCondition, mb.acquireTime) FROM Badge b LEFT JOIN MemberBadge mb ON b.uid = mb.badge.uid AND mb.member.uid = :memberUid")
    List<MemberBadgeRes> getBadgeListAsMemberBadgeRes(@Param("memberUid") Long memberUid);
}
