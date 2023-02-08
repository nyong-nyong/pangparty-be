package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
    boolean existsByEmail(String email);
}
