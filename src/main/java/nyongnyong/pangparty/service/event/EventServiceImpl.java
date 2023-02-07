package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.event.EventIntroduceRequestDto;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.repository.event.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Page<Event> findEvents(Pageable pageable) {
        return eventRepository.findEvents(pageable).map(EventIntroduceRequestDto::from);
    }

//    @Override
//    public List<Event> findEventList() {
//        return eventRepository.findAll();
//    }
}
