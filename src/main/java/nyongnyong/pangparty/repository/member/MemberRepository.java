package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m.uid from Member m left join MemberProfile mp where mp.id = :id")
    Long findUidById(String id);

    @Query("select m from Member m left join fetch m.memberProfile mp where mp.id = :id")
    Member findMemberById(String id);

    @Query("select m from Member m where m.uid = :uid")
    Member findMemberByUid(Long uid);
}
