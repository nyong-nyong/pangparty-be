package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.dto.member.MemberProfilePictureSimpleRes;
import nyongnyong.pangparty.entity.member.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long>, MemberProfileRepositoryCustom {
    @Query("select m from MemberProfile m where m.id = :memberId")
    Optional<MemberProfile> findByMemberId(@Param("memberId") String memberId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update MemberProfile m " +
            "set m.id = :memberId, m.name = :name, " +
            "m.imgUrl = :imgUrl, m.introduction = :introduction " +
            "where m.memberUid = :memberUid")
    int updateMemberProfile(@Param("memberUid") Long memberUid, @Param("memberId") String memberId, @Param("name") String name, @Param("imgUrl") String imgUrl, @Param("introduction") String introduction);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update MemberProfile m " +
            "set m.imgUrl = :profileUrl " +
            "where m.memberUid = :memberUid")
    void updateImgUrl(@Param("memberUid") Long memberUid,@Param("profileUrl") String profileUrl);
}
