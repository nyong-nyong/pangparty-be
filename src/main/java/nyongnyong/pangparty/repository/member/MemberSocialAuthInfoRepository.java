package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.MemberSocialAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSocialAuthInfoRepository extends JpaRepository<MemberSocialAuthInfo, Long> {
}
