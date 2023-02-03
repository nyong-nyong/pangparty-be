package nyongnyong.pangparty.repository.badge;

import nyongnyong.pangparty.entity.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
