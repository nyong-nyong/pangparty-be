package nyongnyong.pangparty.service.event;

import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.entity.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface EventService {
//    Page<Event> findEvents(Pageable pageable);
//    List<Event> findEventList();

    boolean isExistEventByEventUid(Long eventUid);

    List<EventCard> findReceivedEventsByMemberId(String memberId);
}
