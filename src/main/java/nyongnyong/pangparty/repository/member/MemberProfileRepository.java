package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
    MemberProfile findMemberProfileById(String id);
    boolean existsById(String id);
}
