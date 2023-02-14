package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.entity.feed.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    @Query("select count(pl) from PostLike pl where pl.post.uid = :postUid")
    Long countByPostUid(Long postUid);

    @Query("select pl from PostLike pl where pl.post.uid = :postUid and pl.member.uid = :memberUid")
    Optional<PostLike> findByPostUidAndMemberUid(Long postUid, Long memberUid);

    boolean existsByPostUidAndMemberUid(Long uid, Long memberUid);
}
