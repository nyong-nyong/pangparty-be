package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.event.EventParticipant;
import nyongnyong.pangparty.repository.event.EventParticipantRepository;
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
public class EventParticipantRepositoryTest {

    @Autowired
    EventParticipantRepository eventParticipantRepository;

    @Test
    @Transactional
    public void testEventParticipant(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

        EventParticipant eventParticipant = new EventParticipant(LocalDateTime.parse("2023-01-18 10:24:00", formatter));
        EventParticipant savedEventParticipant = eventParticipantRepository.save(eventParticipant);

        EventParticipant findEventParticipant = eventParticipantRepository.findById(savedEventParticipant.getUid()).get();

        Assertions.assertThat(findEventParticipant.getJoinTime()).isEqualTo(eventParticipant.getJoinTime());
    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        EventParticipant eventParticipant1 = new EventParticipant(LocalDateTime.parse("2023-01-19 10:24:00", formatter));
        EventParticipant eventParticipant2 = new EventParticipant(LocalDateTime.parse("2023-01-20 10:24:00", formatter));
        eventParticipantRepository.save(eventParticipant1);
        eventParticipantRepository.save(eventParticipant2);

        // 단건 조회 검증
        EventParticipant findEventParticipant1 = eventParticipantRepository.findById(eventParticipant1.getUid()).get();
        EventParticipant findEventParticipant2 = eventParticipantRepository.findById(eventParticipant2.getUid()).get();
        assertThat(findEventParticipant1).isEqualTo(eventParticipant1);
        assertThat(findEventParticipant2).isEqualTo(eventParticipant2);

        // 리스트 조회 검증
        List<EventParticipant> all = eventParticipantRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = eventParticipantRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        eventParticipantRepository.delete(eventParticipant1);
        eventParticipantRepository.delete(eventParticipant2);

        long deletedCount = eventParticipantRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
