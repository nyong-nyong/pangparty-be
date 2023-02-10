package nyongnyong.pangparty.service.event;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.dto.event.EventCreateReq;
import nyongnyong.pangparty.dto.event.EventIntroduceRes;
import nyongnyong.pangparty.entity.event.Event;

import java.util.List;

public interface EventService {

    boolean isExistEventByEventUid(Long eventUid);

    Event addEventAndEventTarget(Long hostUid, EventCreateReq eventCreateReq);
    List<EventCard> findReceivedEventsByMemberId(String memberId);

    List<EventCard> findHostEventsByMemberId(String memberId);

    List<EventCard> findInvolvingEventsByMemberId(String memberId);

    List<EventCard> findInvolvedEventsByMemberId(String memberId);

    EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid);

    Long addRollingPaper(Long eventUid);
    Event getEventByEventUid(Long eventUid);
}
