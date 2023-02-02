package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.EventLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLikeRepository extends JpaRepository<EventLike, Long> {
}
