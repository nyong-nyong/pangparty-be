package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.common.FeedType;
import nyongnyong.pangparty.entity.feed.Feed;
import nyongnyong.pangparty.repository.feed.FeedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class FeedRepositoryTest {

    @Autowired
    FeedRepository feedRepository;

    @Test
    public void testFeed() {
        System.out.println("=========START TEST FEED=========");

        Feed feed = new Feed(FeedType.FANFARE, LocalDateTime.now());
        Feed savedFeed = feedRepository.save(feed);

        Feed findFeed = feedRepository.findById(savedFeed.getUid()).get();

        Assertions.assertThat(findFeed.getFeedType()).isEqualTo(feed.getFeedType());
        Assertions.assertThat(findFeed.getCreateTime()).isEqualTo(feed.getCreateTime());
        Assertions.assertThat(findFeed).isEqualTo(feed);

        feedRepository.delete(savedFeed);

        System.out.println("=========END TEST FEED=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE FEED=========");

        Feed feed1 = new Feed(FeedType.FANFARE, LocalDateTime.now());
        Feed feed2 = new Feed(FeedType.WRITE_POST, LocalDateTime.now());
        feedRepository.save(feed1);
        feedRepository.save(feed2);

        System.out.println("=========END SAVE FEED=========");

        System.out.println("=========START FIND FEED=========");

        // 단건 조회 검증
        Feed findFeed1 = feedRepository.findById(feed1.getUid()).get();
        Feed findFeed2 = feedRepository.findById(feed2.getUid()).get();
        Assertions.assertThat(findFeed1).isEqualTo(feed1);
        Assertions.assertThat(findFeed2).isEqualTo(feed2);

        System.out.println("=========END FIND FEED=========");

        System.out.println("=========START FIND FEED LIST=========");
        //리스트 조회 검증
        List<Feed> all = feedRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND FEED LIST=========");

        System.out.println("=========START COUNT FEED=========");
        //카운트 검증
        long count = feedRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT FEED=========");

        System.out.println("=========START DELETE FEED=========");
        //삭제 검증
        feedRepository.delete(feed1);
        feedRepository.delete(feed2);
        long deletedCount = feedRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE FEED=========");
    }
}
