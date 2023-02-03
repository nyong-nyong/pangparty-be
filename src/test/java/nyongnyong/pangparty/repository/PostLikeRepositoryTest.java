package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.feed.PostLike;
import nyongnyong.pangparty.repository.feed.PostLikeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class PostLikeRepositoryTest {

    @Autowired
    PostLikeRepository postLikeRepository;

    @Test
    public void testPostLike() {
        System.out.println("=========START TEST POST LIKE=========");

        PostLike postLike = new PostLike(LocalDateTime.now());
        PostLike savedPostLike = postLikeRepository.save(postLike);

        PostLike findPostLike = postLikeRepository.findById(savedPostLike.getUid()).get();

        Assertions.assertThat(findPostLike.getLikeTime()).isEqualTo(postLike.getLikeTime());
        Assertions.assertThat(findPostLike).isEqualTo(postLike);

        postLikeRepository.delete(postLike);

        System.out.println("=========END TEST POST LIKE=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE POST LIKE=========");

        PostLike postLike1 = new PostLike(LocalDateTime.now());
        PostLike postLike2 = new PostLike(LocalDateTime.now());
        postLikeRepository.save(postLike1);
        postLikeRepository.save(postLike2);

        System.out.println("=========END SAVE POST LIKE=========");

        System.out.println("=========START FIND POST LIKE=========");

        // 단건 조회 검증
        PostLike findPostLike1 = postLikeRepository.findById(postLike1.getUid()).get();
        PostLike findPostLike2 = postLikeRepository.findById(postLike2.getUid()).get();
        Assertions.assertThat(findPostLike1).isEqualTo(postLike1);
        Assertions.assertThat(findPostLike2).isEqualTo(postLike2);

        System.out.println("=========END FIND POST LIKE=========");

        System.out.println("=========START FIND POST LIKE LIST=========");
        //리스트 조회 검증
        List<PostLike> all = postLikeRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND POST LIKE LIST=========");

        System.out.println("=========START COUNT POST LIKE=========");
        //카운트 검증
        long count = postLikeRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT POST LIKE=========");

        System.out.println("=========START DELETE POST LIKE=========");
        //삭제 검증
        postLikeRepository.delete(postLike1);
        postLikeRepository.delete(postLike2);
        long deletedCount = postLikeRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE POST LIKE=========");
    }
}
