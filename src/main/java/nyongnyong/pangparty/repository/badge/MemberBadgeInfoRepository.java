package nyongnyong.pangparty.repository.badge;

import nyongnyong.pangparty.entity.badge.MemberBadgeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberBadgeInfoRepository extends JpaRepository<MemberBadgeInfo, Long> {

    @Query("select mb from MemberBadgeInfo mb where mb.member.uid = :memberUid")
    MemberBadgeInfo findByMemberUid(Long memberUid);

    @Query("update MemberBadgeInfo mb set mb.loginCount = mb.loginCount + 1 where mb.member.uid = :uid")
    void updateLoginCount(Long uid);

    @Query("update MemberBadgeInfo mb set mb.receiveCount = mb.receiveCount + 1 where mb.member.uid = :uid")
    void updateReceiveCount(Long uid);

    @Query("update MemberBadgeInfo mb set mb.receiveCount = mb.receiveCount + 1 where mb.member.memberProfile.id = :id")
    void updateReceiveCountById(String id);

    @Query("update MemberBadgeInfo mb set mb.participateCount = mb.participateCount + 1 where mb.member.uid = :uid")
    void updateParticipateCount(Long uid);
}
