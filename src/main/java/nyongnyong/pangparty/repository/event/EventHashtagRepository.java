package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.EventHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventHashtagRepository extends JpaRepository<EventHashtag, Long> {
}
