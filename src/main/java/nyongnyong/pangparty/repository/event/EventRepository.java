package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

//    @Query("select e from Event e order by e.uid desc")
    Page<Event> findEvents(Pageable pageable);

}
