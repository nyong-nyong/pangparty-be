package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.entity.member.Friendship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query(value = "select count(f) from Friendship f where f.followee.uid = :followee")
    Long countByFollowee(@Param("followee") Long followee);

    @Query(value = "select count(f) from Friendship f where f.follower.uid = :follower")
    Long countByFollower(@Param("follower") Long follower);

    @Query(value = "select f from Friendship f where f.follower.uid = :follower and f.followee.uid = :followee")
    Optional<Friendship> findByFollowerAndFollowee(@Param("follower") Long follower,@Param("followee")  Long followee);

    @Query(value = "select f from Friendship f where f.followee.uid = :followee")
    List<Friendship> findAllByFollowee(@Param("followee") Long followee);

    @Query(value = "select new nyongnyong.pangparty.dto.member.FriendshipRes(mp.id, mp.name, mp.imgUrl) " +
            "from Friendship f inner join MemberProfile mp on f.followee.uid = mp.memberUid where f.follower.uid = :follower")
    List<FriendshipRes> findAllByFollower(@Param("follower") Long follower);

    @Query(value = "select new nyongnyong.pangparty.dto.member.FriendshipRes(mp.id, mp.name, mp.imgUrl) " +
            "from Friendship f inner join MemberProfile mp on f.followee.uid = mp.memberUid where f.follower.uid = :follower")
    Page<FriendshipRes> findAllByFollower(@Param("follower") Long follower, Pageable pageable);

    @Query(value = "select new nyongnyong.pangparty.dto.member.FriendshipRes(mp.id, mp.name, mp.imgUrl) " +
            "from Friendship f inner join MemberProfile mp on f.follower.uid = mp.memberUid where f.followee.uid = :followee")
    Page<FriendshipRes> findAllByFollowee(@Param("followee") Long followee, Pageable pageable);
}

