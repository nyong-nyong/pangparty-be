package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.EventTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTargetRepository extends JpaRepository<EventTarget, Long> {
}
