package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.hashtag.Hashtag;
import nyongnyong.pangparty.repository.hashtag.HashtagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class HashtagRepositoryTest {

    @Autowired
    HashtagRepository hashtagRepository;

    @Test
    public void testHashtag() {
        System.out.println("=========START TEST HASHTAG=========");

        Hashtag hashtag = new Hashtag("영빈아생일축하해", LocalDateTime.now());
        Hashtag savedHashtag = hashtagRepository.save(hashtag);

        Hashtag findHashtag = hashtagRepository.findById(savedHashtag.getUid()).get();

        Assertions.assertThat(findHashtag.getName()).isEqualTo(hashtag.getName());
        Assertions.assertThat(findHashtag.getCreateTime()).isEqualTo(hashtag.getCreateTime());
        Assertions.assertThat(findHashtag).isEqualTo(hashtag);

        System.out.println("=========END TEST HASHTAG=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE HASHTAG=========");

        Hashtag hashtag1 = new Hashtag("영빈아생일축하해", LocalDateTime.now());
        Hashtag hashtag2 = new Hashtag("생축생축생축", LocalDateTime.now());
        hashtagRepository.save(hashtag1);
        hashtagRepository.save(hashtag2);

        System.out.println("=========END SAVE HASHTAG=========");

        System.out.println("=========START FIND HASHTAG=========");

        // 단건 조회 검증
        Hashtag findHashtag1 = hashtagRepository.findById(hashtag1.getUid()).get();
        Hashtag findHashtag2 = hashtagRepository.findById(hashtag2.getUid()).get();
        Assertions.assertThat(findHashtag1).isEqualTo(hashtag1);
        Assertions.assertThat(findHashtag2).isEqualTo(hashtag2);

        System.out.println("=========END FIND HASHTAG=========");

        System.out.println("=========START FIND HASHTAG LIST=========");
        //리스트 조회 검증
        List<Hashtag> all = hashtagRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND HASHTAG LIST=========");

        System.out.println("=========START COUNT HASHTAG=========");
        //카운트 검증
        long count = hashtagRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT HASHTAG=========");

        System.out.println("=========START DELETE HASHTAG=========");
        //삭제 검증
        hashtagRepository.delete(hashtag1);
        hashtagRepository.delete(hashtag2);
        long deletedCount = hashtagRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE HASHTAG=========");
    }
}
