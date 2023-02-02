package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.repository.event.EventRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    @Test
    @Transactional
    public void testEvent(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

        Event event = new Event("영빈이생일파뤼", "2월 영빈이 생일인데~~~ 축하합시돠",
                "https://media.istockphoto.com/id/1298329918/photo/birthday-celebratory-toast-with-string-lights-and-champagne-silhouettes.jpg?s=612x612&w=0&k=20&c=PaDeMR5-r0NdlxghuVF9tRqR5XkCdNdTzxrkofv0Syk=",
                LocalDate.parse("2023-02-19 00:00:00", formatter), LocalDateTime.parse("2023-01-19 10:24:00", formatter), LocalDateTime.parse("2023-02-08 10:24:00", formatter),
                LocalDateTime.parse("2023-02-10 10:24:00", formatter), LocalDateTime.parse("2023-02-19 10:00:00", formatter), LocalDateTime.parse("2023-02-19 10:20:00", formatter), true);
        Event savedEvent = eventRepository.save(event);

        Event findEvent = eventRepository.findById(savedEvent.getUid()).get();

        Assertions.assertThat(findEvent.getEventName()).isEqualTo(event.getEventName());
        Assertions.assertThat(findEvent.getIntroduction()).isEqualTo(event.getIntroduction());
        Assertions.assertThat(findEvent.getImgUrl()).isEqualTo(event.getImgUrl());
        Assertions.assertThat(findEvent.getDDay()).isEqualTo(event.getDDay());
        Assertions.assertThat(findEvent.getCreateTime()).isEqualTo(event.getCreateTime());
        Assertions.assertThat(findEvent.getModifyTime()).isEqualTo(event.getModifyTime());
        Assertions.assertThat(findEvent.getStartTime()).isEqualTo(event.getStartTime());
        Assertions.assertThat(findEvent.getEndTime()).isEqualTo(event.getEndTime());
        Assertions.assertThat(findEvent.getPartyTime()).isEqualTo(event.getPartyTime());
        Assertions.assertThat(findEvent.isPrivate()).isEqualTo(event.isPrivate());
    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Event event1 = new Event("영빈이생일파뤼1", "2월 영빈이 생일인데~~~ 축하합시돠",
                "https://media.istockphoto.com/id/1298329918/photo/birthday-celebratory-toast-with-string-lights-and-champagne-silhouettes.jpg?s=612x612&w=0&k=20&c=PaDeMR5-r0NdlxghuVF9tRqR5XkCdNdTzxrkofv0Syk=",
                LocalDate.parse("2023-02-19 00:00:00", formatter), LocalDateTime.parse("2023-01-19 10:24:00", formatter), LocalDateTime.parse("2023-02-08 10:24:00", formatter),
                LocalDateTime.parse("2023-02-10 10:24:00", formatter), LocalDateTime.parse("2023-02-19 10:00:00", formatter), LocalDateTime.parse("2023-02-19 10:20:00", formatter), true);
        Event event2 = new Event("영빈이생일파뤼2", "2월 영빈이 생일인데~~~ 축하합시돠",
                "https://media.istockphoto.com/id/1298329918/photo/birthday-celebratory-toast-with-string-lights-and-champagne-silhouettes.jpg?s=612x612&w=0&k=20&c=PaDeMR5-r0NdlxghuVF9tRqR5XkCdNdTzxrkofv0Syk=",
                LocalDate.parse("2023-02-19 00:00:00", formatter), LocalDateTime.parse("2023-01-19 10:24:00", formatter), LocalDateTime.parse("2023-02-08 10:24:00", formatter),
                LocalDateTime.parse("2023-02-10 10:24:00", formatter), LocalDateTime.parse("2023-02-19 10:00:00", formatter), LocalDateTime.parse("2023-02-19 10:20:00", formatter), true);
        eventRepository.save(event1);
        eventRepository.save(event2);

        // 단건 조회 검증
        Event findEvent1 = eventRepository.findById(event1.getUid()).get();
        Event findEvent2 = eventRepository.findById(event2.getUid()).get();
        assertThat(findEvent1).isEqualTo(event1);
        assertThat(findEvent2).isEqualTo(event2);

        // 리스트 조회 검증
        List<Event> all = eventRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = eventRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        eventRepository.delete(event1);
        eventRepository.delete(event2);

        long deletedCount = eventRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
