package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.entity.feed.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {


    @Query("select new nyongnyong.pangparty.dto.feed.PostCommentRes(pc.uid, pc.member.memberProfile.id, pc.content, pc.createTime) " +
            "from PostComment pc where pc.post.uid = :postUid " +
            "order by pc.createTime desc")
    Page<PostCommentRes> findAllByPostUid(Long postUid, Pageable pageable);

    @Query("select pc from PostComment pc where pc.uid = :commentUid")
    Optional<PostComment> findByUid(Long commentUid);

    boolean existsByPostUidAndMemberUid(Long uid, Long memberUid);

    Long countByPostUid(Long uid);
}
