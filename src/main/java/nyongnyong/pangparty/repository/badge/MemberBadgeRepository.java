package nyongnyong.pangparty.repository.badge;

import nyongnyong.pangparty.entity.badge.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

    @Query("select mb from MemberBadge mb where mb.member.uid = :memberUid")
    List<MemberBadge> findByMemberUid(Long memberUid);

    @Query("select mb from MemberBadge mb where mb.member.uid = :memberUid and mb.badge.uid = :badgeUid")
    MemberBadge findByMemberUidAndBadgeUid(Long memberUid, Long badgeUid);
}