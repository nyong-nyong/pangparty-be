package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.entity.feed.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByUid(Long uid);

    @Query("select p from Post p where p.uid = :postUid")
    Post findByUid(Long postUid);
}
