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
import nyongnyong.pangparty.repository.member.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Override
    public boolean isExistEventByEventUid(Long eventUid) {
        return eventRepository.existsById(eventUid);
    }

    @Override
    public Long addEventAndEventTarget(EventCreateReq eventCreateReq) {
        Event event = toEventEntity(eventCreateReq);
        eventRepository.save(event);
        eventTargetRepository.save(toEventTargetEntity(eventCreateReq, event));
        return event.getUid();
    }

    private Event toEventEntity(EventCreateReq eventCreateReq){
        return Event.builder()
                .eventName(eventCreateReq.getEventName())
                .introduction(eventCreateReq.getIntroduction())
                .imgUrl(eventCreateReq.getImgUrl())
                .dDay(eventCreateReq.getDDay()).build();
    }

//    private List<EventHashtag> toEventHashtagEntity(EventCreateReq eventCreateReq){
//        List<EventHashtag> eventHashtags;
//        for (String hashtag : eventCreateReq.getHashtags()) {
//            EventHashtag eventHashtag = EventHashtag.builder()
//                    .hashtag(hashtag)
//                    .build();
//            eventHashtags.add(eventHashtag);
//        }
//        EventHashtag.builder()
//                .event()
//                .addTime(LocalDateTime.now()).build();
//
//        return
//    }

    private EventTarget toEventTargetEntity(EventCreateReq eventCreateReq, Event event){
        return EventTarget.builder()
                .targetMember(memberRepository.findMemberById(eventCreateReq.getTargetId()))
                .event(event)
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
