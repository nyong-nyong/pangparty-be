package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.event.EventHashtag;
import nyongnyong.pangparty.entity.event.EventLike;
import nyongnyong.pangparty.repository.event.EventHashtagRepository;
import nyongnyong.pangparty.repository.event.EventLikeRepository;
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
public class EventLikeRepositoryTest {

    @Autowired
    EventLikeRepository eventLikeRepository;

    @Test
    @Transactional
    public void testEventLike(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

        EventLike eventLike = new EventLike(LocalDateTime.parse("2023-01-18 10:24:00", formatter));
        EventLike savedEventLike = eventLikeRepository.save(eventLike);

        EventLike findEventLike = eventLikeRepository.findById(savedEventLike.getUid()).get();

        Assertions.assertThat(findEventLike.getLikeTime()).isEqualTo(eventLike.getLikeTime());
    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        EventLike eventLike1 = new EventLike(LocalDateTime.parse("2023-01-19 10:24:00", formatter));
        EventLike eventLike2 = new EventLike(LocalDateTime.parse("2023-01-20 10:24:00", formatter));
        eventLikeRepository.save(eventLike1);
        eventLikeRepository.save(eventLike2);

        // 단건 조회 검증
        EventLike findEventLike1 = eventLikeRepository.findById(eventLike1.getUid()).get();
        EventLike findEventLike2 = eventLikeRepository.findById(eventLike2.getUid()).get();
        assertThat(findEventLike1).isEqualTo(eventLike1);
        assertThat(findEventLike2).isEqualTo(eventLike2);

        // 리스트 조회 검증
        List<EventLike> all = eventLikeRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = eventLikeRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        eventLikeRepository.delete(eventLike1);
        eventLikeRepository.delete(eventLike2);

        long deletedCount = eventLikeRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
