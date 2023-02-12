package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {

    @Query("select m from MemberProfile m where m.id = :memberId")
    Optional<MemberProfile> findByMemberId(String memberId);
}
