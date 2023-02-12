package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.dto.feed.PostRes;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.feed.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByUid(Long uid);

    Optional<Post> findByUid(Long uid);

    @Query("select new nyongnyong.pangparty.dto.feed.PostRes(p.uid, p.event.uid, p.member.memberProfile.id, p.content, " +
            "p.imgUrl, p.createTime, p.modifyTime) from Post p where p.uid = :postUid")
    PostRes findPostResByUid(Long postUid);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Post p set p.event = :event, p.content = :content, p.imgUrl = :imgUrl where p.uid = :postUid")
    int updatePost(Long postUid, Event event, String content, String imgUrl);
}
