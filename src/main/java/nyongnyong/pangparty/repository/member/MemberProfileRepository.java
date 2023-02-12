package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
    @Query("select m from MemberProfile m where m.id = :memberId")
    Optional<MemberProfile> findByMemberId(String memberId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update MemberProfile m " +
            "set m.id = :memberId, m.name = :name, " +
            "m.imgUrl = :imgUrl, m.introduction = :introduction " +
            "where m.memberUid = :memberUid")
    int updateMemberProfile(Long memberUid, String memberId, String name, String imgUrl, String introduction);

}
