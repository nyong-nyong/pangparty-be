package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
//import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.repository.event.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public boolean isExistEventByEventUid(Long eventUid) {
        return eventRepository.existsById(eventUid);
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

}
