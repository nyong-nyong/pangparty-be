package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventHashtag;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import nyongnyong.pangparty.repository.event.EventHashtagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventHashtagServiceImpl implements EventHashtagService {
    private final EventHashtagRepository eventHashtagRepository;
    @Override
    public Long addEventHashtag(Event event, Hashtag hashtag) {
        eventHashtagRepository.save(toEventHashtagEntity(event, hashtag));
        return eventHashtagRepository.findEventHashtagByEventUidAndHashtagUid(event.getUid(), hashtag.getUid()).getUid();
    }

    private EventHashtag toEventHashtagEntity(Event event, Hashtag hashtag) {
        return EventHashtag.builder()
                .event(event)
                .hashtag(hashtag)
                .addTime(LocalDateTime.now())
                .build();
    }
}
