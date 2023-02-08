package nyongnyong.pangparty.repository.auth;

import nyongnyong.pangparty.entity.auth.MemberAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAuthInfoRepository extends JpaRepository<MemberAuthInfo, Long> {

    MemberAuthInfo findByEmail(String email);

    boolean existsByEmail(String email);

}
