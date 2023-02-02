package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
