package nyongnyong.pangparty.service.event;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.dto.event.EventCreateReq;
import nyongnyong.pangparty.dto.event.EventIntroduceRes;

import java.util.List;

public interface EventService {

    boolean isExistEventByEventUid(Long eventUid);

    Long addEvent(EventCreateReq eventCreateReq);
    List<EventCard> findReceivedEventsByMemberId(String memberId);

    List<EventCard> findHostEventsByMemberId(String memberId);

    List<EventCard> findInvolvingEventsByMemberId(String memberId);

    List<EventCard> findInvolvedEventsByMemberId(String memberId);

    EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid);
}
