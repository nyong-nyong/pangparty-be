package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.entity.feed.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
