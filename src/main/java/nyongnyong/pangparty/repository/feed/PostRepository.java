package nyongnyong.pangparty.repository.feed;

import nyongnyong.pangparty.dto.feed.FeedDto;
import nyongnyong.pangparty.dto.feed.PostRes;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.feed.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByUid(Long uid);

    Optional<Post> findByUid(Long uid);

    @Query("select new nyongnyong.pangparty.dto.feed.PostRes(p.uid, p.event.uid, p.member.memberProfile.id, p.member.memberProfile.imgUrl, p.title, p.content, " +
            "p.imgUrl, p.createTime, p.modifyTime) from Post p where p.uid = :postUid")
    PostRes findPostResByUid(@Param("postUid") Long postUid);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Post p set p.event = :event, p.title = :title, p.content = :content, p.imgUrl = :imgUrl where p.uid = :postUid")
    int updatePost(@Param("postUid") Long postUid, @Param("event") Event event, @Param("title") String title, @Param("content") String content, @Param("imgUrl") String imgUrl);

    // TODO: profileImgUrl FeedDto에 추가, 쿼리에도 추가하기
    @Query("select distinct new nyongnyong.pangparty.dto.feed.FeedDto(p.uid, p.event.uid, p.member.memberProfile.id," +
            " p.title, p.content, p.imgUrl, mp.member.memberProfile.imgUrl, p.createTime, p.modifyTime) from Post p" +
//            " left join Event e on e.uid = p.event.uid" +
            " left join Member m on p.member.uid = m.uid" +
            " left join MemberProfile mp on m.uid = mp.member.uid" +
            " left join Friendship f on m.uid = f.followee.uid" +
            " where f.follower.uid = :memberUid or p.member.uid = :memberUid" +
            " order by p.createTime desc")
    List<FeedDto> findPostsByMemberUid(@Param("memberUid") Long memberUid);

    // TODO: profileImgUrl FeedDto에 추가, 쿼리에도 추가하기
    @Query("select distinct new nyongnyong.pangparty.dto.feed.FeedDto(p.uid, p.event.uid, p.member.memberProfile.id," +
            " p.title, p.content, p.imgUrl, mp.member.memberProfile.imgUrl,  p.createTime, p.modifyTime) from Post p" +
            " left join Member m on p.member.uid = m.uid" +
            " left join MemberProfile mp on m.uid = mp.member.uid" +
            " where p.member.memberProfile.id = :memberId" +
            " order by p.createTime desc")
    List<FeedDto> findMyPostsByMemberId(@Param("memberId") String memberId);
}
