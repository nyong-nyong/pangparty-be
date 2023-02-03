package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.feed.Post;
import nyongnyong.pangparty.repository.feed.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void testPost() {
        System.out.println("=========START TEST POST=========");

        Post post = new Post("오늘 딱 축하하기 좋은 날!", LocalDateTime.now(), null, 0);
        Post savedPost = postRepository.save(post);

        Post findPost = postRepository.findById(savedPost.getUid()).get();

        Assertions.assertThat(findPost.getContent()).isEqualTo(post.getContent());
        Assertions.assertThat(findPost.getCreateTime()).isEqualTo(post.getCreateTime());
        Assertions.assertThat(findPost.getModifyTime()).isEqualTo(post.getModifyTime());
        Assertions.assertThat(findPost.getHit()).isEqualTo(post.getHit());
        Assertions.assertThat(findPost).isEqualTo(post);

        System.out.println("=========END TEST POST=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE POST=========");

        Post post1 = new Post("오늘 딱 축하하기 좋은 날!", LocalDateTime.now(), null, 0);
        Post post2 = new Post("나는 내가 좋아", LocalDateTime.now(), LocalDateTime.now(), 0);
        postRepository.save(post1);
        postRepository.save(post2);

        System.out.println("=========END SAVE POST=========");

        System.out.println("=========START FIND POST=========");

        // 단건 조회 검증
        Post findPost1 = postRepository.findById(post1.getUid()).get();
        Post findPost2 = postRepository.findById(post2.getUid()).get();
        Assertions.assertThat(findPost1).isEqualTo(post1);
        Assertions.assertThat(findPost2).isEqualTo(post2);

        System.out.println("=========END FIND POST=========");

        System.out.println("=========START FIND POST LIST=========");
        //리스트 조회 검증
        List<Post> all = postRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND POST LIST=========");

        System.out.println("=========START COUNT POST=========");
        //카운트 검증
        long count = postRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT POST=========");

        System.out.println("=========START DELETE POST=========");
        //삭제 검증
        postRepository.delete(post1);
        postRepository.delete(post2);
        long deletedCount = postRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE POST=========");
    }
}
