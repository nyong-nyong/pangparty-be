package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.entity.member.Friendship;
import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query(value = "select count(f) from Friendship f where f.followee.uid = :followee")
    Long countByFollowee(Long followee);

    @Query(value = "select count(f) from Friendship f where f.follower.uid = :follower")
    Long countByFollower(Long follower);

    @Query(value = "select f from Friendship f where f.follower.uid = :follower and f.followee.uid = :followee")
    Optional<Friendship> findByFollowerAndFollowee(Long follower, Long followee);

    @Query(value = "select new nyongnyong.pangparty.dto.member.FriendshipRes(f.followee.uid, mp.id, mp.imgUrl) " +
            "from Friendship f inner join MemberProfile mp on f.followee.uid = mp.memberUid where f.follower.uid = :follower")
    List<FriendshipRes> findAllByFollower(Long follower);

    @Query(value = "select new nyongnyong.pangparty.dto.member.FriendshipRes(f.followee.uid, mp.id, mp.imgUrl) " +
            "from Friendship f inner join MemberProfile mp on f.followee.uid = mp.memberUid where f.follower.uid = :follower")
    Page<FriendshipRes> findAllByFollower(Long follower, Pageable pageable);

    @Query(value = "select new nyongnyong.pangparty.dto.member.FriendshipRes(f.follower.uid, mp.id, mp.imgUrl) " +
            "from Friendship f inner join MemberProfile mp on f.follower.uid = mp.memberUid where f.followee.uid = :followee")
    Page<FriendshipRes> findAllByFollowee(Long followee, Pageable pageable);
}

