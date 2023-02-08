package nyongnyong.pangparty.service.event;
import nyongnyong.pangparty.dto.event.EventCard;
import java.util.List;

public interface EventService {

    boolean isExistEventByEventUid(Long eventUid);

    List<EventCard> findReceivedEventsByMemberId(String memberId);
}
