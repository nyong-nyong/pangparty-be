package nyongnyong.pangparty.repository.badge;

import nyongnyong.pangparty.entity.badge.MemberBadgeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBadgeInfoRepository extends JpaRepository<MemberBadgeInfo, Long> {
}
