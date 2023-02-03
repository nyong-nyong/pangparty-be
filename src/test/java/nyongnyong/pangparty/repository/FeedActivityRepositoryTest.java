package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.common.ActivityType;
import nyongnyong.pangparty.entity.feed.FeedActivity;
import nyongnyong.pangparty.repository.feed.FeedActivityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class FeedActivityRepositoryTest {

    @Autowired
    FeedActivityRepository feedActivityRepository;

    @Test
    public void testFeedActivity() {
        System.out.println("=========START TEST FEED ACTIVITY=========");

        FeedActivity feedActivity = new FeedActivity(ActivityType.HOST_EVENT, LocalDateTime.now());
        FeedActivity savedFeedActivity = feedActivityRepository.save(feedActivity);

        FeedActivity findFeedActivity = feedActivityRepository.findById(savedFeedActivity.getUid()).get();

        Assertions.assertThat(findFeedActivity.getActivityType()).isEqualTo(feedActivity.getActivityType());
        Assertions.assertThat(findFeedActivity.getActivityTime()).isEqualTo(feedActivity.getActivityTime());
        Assertions.assertThat(findFeedActivity).isEqualTo(feedActivity);

        feedActivityRepository.delete(feedActivity);

        System.out.println("=========END TEST FEED ACTIVITY=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE FEED ACTIVITY=========");

        FeedActivity feedActivity1 = new FeedActivity(ActivityType.HOST_EVENT, LocalDateTime.now());
        FeedActivity feedActivity2 = new FeedActivity(ActivityType.JOIN_EVENT_ROLLINGPAPER, LocalDateTime.now());
        feedActivityRepository.save(feedActivity1);
        feedActivityRepository.save(feedActivity2);

        System.out.println("=========END SAVE FEED ACTIVITY=========");

        System.out.println("=========START FIND FEED ACTIVITY=========");

        // 단건 조회 검증
        FeedActivity findFeedActivity1 = feedActivityRepository.findById(feedActivity1.getUid()).get();
        FeedActivity findFeedActivity2 = feedActivityRepository.findById(feedActivity2.getUid()).get();
        Assertions.assertThat(findFeedActivity1).isEqualTo(feedActivity1);
        Assertions.assertThat(findFeedActivity2).isEqualTo(feedActivity2);

        System.out.println("=========END FIND FEED ACTIVITY=========");

        System.out.println("=========START FIND FEED ACTIVITY LIST=========");
        //리스트 조회 검증
        List<FeedActivity> all = feedActivityRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND FEED ACTIVITY LIST=========");

        System.out.println("=========START COUNT FEED ACTIVITY=========");
        //카운트 검증
        long count = feedActivityRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT FEED ACTIVITY=========");

        System.out.println("=========START DELETE FEED ACTIVITY=========");
        //삭제 검증
        feedActivityRepository.delete(feedActivity1);
        feedActivityRepository.delete(feedActivity2);
        long deletedCount = feedActivityRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE FEED ACTIVITY=========");
    }
}
