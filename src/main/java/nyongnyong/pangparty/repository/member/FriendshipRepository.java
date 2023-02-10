package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.entity.member.Friendship;
import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Long countByFollowee(Member followee);
    Long countByFollower(Member follower);

    @Query("select f from Friendship f where f.follower = :follower and f.followee = :followee")
    Optional<Friendship> findByFollowerAndFollowee(Long follower, Long followee);

    @Query("select fr from Friendship f inner join MemberProfile mp on f.followee = mp.member where f.follower = :follower")
    List<FriendshipRes> findAllByFollower(Long follower);

    @Query("select fr from Friendship f inner join MemberProfile mp on f.followee = mp.member where f.follower = :follower")
    List<FriendshipRes> findAllByFollower(Long follower, Pageable pageable);

    @Query("select fr from Friendship f inner join MemberProfile mp on f.follower = mp.member where f.followee = :followee")
    List<FriendshipRes> findAllByFollowee(Long followee, Pageable pageable);
}

