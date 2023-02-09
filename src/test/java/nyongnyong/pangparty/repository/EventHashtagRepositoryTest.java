package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventHashtag;
import nyongnyong.pangparty.repository.event.EventHashtagRepository;
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
public class EventHashtagRepositoryTest {

    @Autowired
    EventHashtagRepository eventHashtagRepository;

    @Test
    @Transactional
    public void testEventHashtag(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

//        EventHashtag eventHashtag = new EventHashtag(LocalDateTime.parse("2023-01-18 10:24:00", formatter));
        EventHashtag eventHashtag = new EventHashtag(LocalDateTime.parse("2023-01-18 10:24:00", formatter));
        EventHashtag savedEventHashtag = eventHashtagRepository.save(eventHashtag);

        EventHashtag findEventHashtag = eventHashtagRepository.findById(savedEventHashtag.getUid()).get();

        Assertions.assertThat(findEventHashtag.getAddTime()).isEqualTo(eventHashtag.getAddTime());
    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        EventHashtag eventHashtag1 = new EventHashtag(LocalDateTime.parse("2023-01-19 10:24:00", formatter));
        EventHashtag eventHashtag2 = new EventHashtag(LocalDateTime.parse("2023-01-20 10:24:00", formatter));
        eventHashtagRepository.save(eventHashtag1);
        eventHashtagRepository.save(eventHashtag2);

        // 단건 조회 검증
        EventHashtag findEventHashtag1 = eventHashtagRepository.findById(eventHashtag1.getUid()).get();
        EventHashtag findEventHashtag2 = eventHashtagRepository.findById(eventHashtag2.getUid()).get();
        assertThat(findEventHashtag1).isEqualTo(eventHashtag1);
        assertThat(findEventHashtag2).isEqualTo(eventHashtag2);

        // 리스트 조회 검증
        List<EventHashtag> all = eventHashtagRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = eventHashtagRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        eventHashtagRepository.delete(eventHashtag1);
        eventHashtagRepository.delete(eventHashtag2);

        long deletedCount = eventHashtagRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
