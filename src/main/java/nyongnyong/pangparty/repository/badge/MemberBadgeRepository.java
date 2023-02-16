package nyongnyong.pangparty.repository.badge;

import io.lettuce.core.dynamic.annotation.Param;
import nyongnyong.pangparty.entity.badge.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

    @Query("select mb from MemberBadge mb where mb.member.uid = :memberUid")
    List<MemberBadge> findByMemberUid(@Param("memberUid") Long memberUid);

    @Query("select mb from MemberBadge mb where mb.member.uid = :memberUid and mb.badge.uid = :badgeUid")
    MemberBadge findByMemberUidAndBadgeUid(@Param("memberUid") Long memberUid, @Param("badgeUid")  Long badgeUid);
}