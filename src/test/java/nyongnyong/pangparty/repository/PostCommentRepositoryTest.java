package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.feed.PostComment;
import nyongnyong.pangparty.repository.feed.PostCommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class PostCommentRepositoryTest {

    @Autowired
    PostCommentRepository postCommentRepository;

    @Test
    public void testPostComment() {
        System.out.println("=========START TEST POST COMMENT=========");

        PostComment postComment = new PostComment("생일 축하해!!!!!", LocalDateTime.now());
        PostComment savedPostComment = postCommentRepository.save(postComment);

        PostComment findPostComment = postCommentRepository.findById(savedPostComment.getUid()).get();

        Assertions.assertThat(findPostComment.getContent()).isEqualTo(postComment.getContent());
        Assertions.assertThat(findPostComment.getCreateTime()).isEqualTo(postComment.getCreateTime());
        Assertions.assertThat(findPostComment).isEqualTo(postComment);

        postCommentRepository.delete(savedPostComment);

        System.out.println("=========END TEST POST COMMENT=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE POST COMMENT=========");

        PostComment postComment1 = new PostComment("생일 축하해!!!!", LocalDateTime.now());
        PostComment postComment2 = new PostComment("HBD!!!!!!!!!", LocalDateTime.now());
        postCommentRepository.save(postComment1);
        postCommentRepository.save(postComment2);

        System.out.println("=========END SAVE POST COMMENT=========");

        System.out.println("=========START FIND POST COMMENT=========");

        // 단건 조회 검증
        PostComment findPostComment1 = postCommentRepository.findById(postComment1.getUid()).get();
        PostComment findPostComment2 = postCommentRepository.findById(postComment2.getUid()).get();
        Assertions.assertThat(findPostComment1).isEqualTo(postComment1);
        Assertions.assertThat(findPostComment2).isEqualTo(postComment2);

        System.out.println("=========END FIND POST COMMENT=========");

        System.out.println("=========START FIND POST COMMENT LIST=========");
        //리스트 조회 검증
        List<PostComment> all = postCommentRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND POST COMMENT LIST=========");

        System.out.println("=========START COUNT POST COMMENT=========");
        //카운트 검증
        long count = postCommentRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT POST COMMENT=========");

        System.out.println("=========START DELETE POST COMMENT=========");
        //삭제 검증
        postCommentRepository.delete(postComment1);
        postCommentRepository.delete(postComment2);
        long deletedCount = postCommentRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE POST COMMENT=========");
    }
}
