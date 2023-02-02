package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.event.EventTarget;
import nyongnyong.pangparty.repository.event.EventTargetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class EventTargetRepositoryTest {

    @Autowired
    EventTargetRepository eventTargetRepository;

    @Test
    @Transactional
    public void testEventTarget(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

        EventTarget eventTarget = new EventTarget(LocalDateTime.parse("2023-01-18 10:24:00", formatter));
        EventTarget savedEventHashtag = eventTargetRepository.save(eventTarget);

        EventTarget findEventHashtag = eventTargetRepository.findById(savedEventHashtag.getUid()).get();

        Assertions.assertThat(findEventHashtag.getAddTime()).isEqualTo(eventTarget.getAddTime());
    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        EventTarget eventTarget1 = new EventTarget(LocalDateTime.parse("2023-01-19 10:24:00", formatter));
        EventTarget eventTarget2 = new EventTarget(LocalDateTime.parse("2023-01-20 10:24:00", formatter));
        eventTargetRepository.save(eventTarget1);
        eventTargetRepository.save(eventTarget2);

        // 단건 조회 검증
        EventTarget findEventTarget1 = eventTargetRepository.findById(eventTarget1.getUid()).get();
        EventTarget findEventTarget2 = eventTargetRepository.findById(eventTarget2.getUid()).get();
        assertThat(findEventTarget1).isEqualTo(eventTarget1);
        assertThat(findEventTarget2).isEqualTo(eventTarget2);

        // 리스트 조회 검증
        List<EventTarget> all = eventTargetRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = eventTargetRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        eventTargetRepository.delete(eventTarget1);
        eventTargetRepository.delete(eventTarget2);

        long deletedCount = eventTargetRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
