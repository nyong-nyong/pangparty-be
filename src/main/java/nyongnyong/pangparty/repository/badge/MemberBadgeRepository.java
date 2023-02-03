package nyongnyong.pangparty.repository.badge;

import nyongnyong.pangparty.entity.badge.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {
}