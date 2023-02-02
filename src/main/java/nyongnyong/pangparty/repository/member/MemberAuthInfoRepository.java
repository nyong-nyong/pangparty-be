package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.MemberAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAuthInfoRepository extends JpaRepository<MemberAuthInfo, Long> {
}
