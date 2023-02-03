package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.entity.feed.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
