package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
//import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.dto.event.EventCreateReq;
import nyongnyong.pangparty.dto.event.EventIntroduceRes;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventHashtag;
import nyongnyong.pangparty.entity.event.EventTarget;
import nyongnyong.pangparty.repository.event.EventHashtagRepository;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.event.EventTargetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventTargetRepository eventTargetRepository;
    private final EventHashtagRepository eventHashtagRepository;

    @Override
    public boolean isExistEventByEventUid(Long eventUid) {
        return eventRepository.existsById(eventUid);
    }

    @Override
    public Long addEvent(EventCreateReq eventCreateReq) {
        Event event = toEventEntity(eventCreateReq);
        EventTarget eventTarget = toEventTargetEntity(eventCreateReq);
        eventRepository.save(event);
        eventTargetRepository.save(eventTarget);
        eventHashtagRepository.saveAll();
        return ;
    }

    private Event toEventEntity(EventCreateReq eventCreateReq){
        return Event.builder()
                .eventName(eventCreateReq.getEventName())
                .introduction(eventCreateReq.getIntroduction())
                .imgUrl(eventCreateReq.getImgUrl())
                .dDay(eventCreateReq.getDDay()).build();
    }

    private EventTarget toEventTargetEntity(EventCreateReq eventCreateReq){
        return EventTarget.builder()
                .addTime(LocalDateTime.now()).build();
    }

    @Override
    public List<EventCard> findReceivedEventsByMemberId(String memberId) {
        return eventRepository.findReceivedEventsByMemberId(memberId);
    }

    @Override
    public List<EventCard> findHostEventsByMemberId(String memberId) {
        return eventRepository.findHostEventsByMemberId(memberId);
    }

    @Override
    public List<EventCard> findInvolvingEventsByMemberId(String memberId) {
        return eventRepository.findInvolvingEventsByMemberId(memberId);
    }

    @Override
    public List<EventCard> findInvolvedEventsByMemberId(String memberId) {
        return eventRepository.findInvolvedEventsByMemberId(memberId);
    }

    @Override
    public EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid) {
        return eventRepository.findEventIntroduceByEventUid(memberUid, eventUid);
    }

}
