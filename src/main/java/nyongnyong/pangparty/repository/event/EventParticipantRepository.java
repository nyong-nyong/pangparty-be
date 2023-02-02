package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {
}
