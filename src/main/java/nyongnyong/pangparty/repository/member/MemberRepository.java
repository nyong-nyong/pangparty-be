package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m.uid from Member m left join MemberProfile mp where mp.id = :id")
    Long findUidById(@Param("id") String id);

    @Query("select m from Member m left join fetch m.memberProfile mp where mp.id = :id")
    Member findMemberById(@Param("id") String id);

    @Query("select m from Member m where m.uid = :uid")
    Member findMemberByUid(@Param("uid") Long uid);

    Member findByEmail(String email);
    boolean existsByEmail(String email);

    String findIdByUid(Long memberUid);
}
