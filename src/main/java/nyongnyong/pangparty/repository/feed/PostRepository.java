package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.entity.feed.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
