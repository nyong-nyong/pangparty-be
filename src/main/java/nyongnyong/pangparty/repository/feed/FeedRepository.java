package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.entity.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
