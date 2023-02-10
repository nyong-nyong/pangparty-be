package nyongnyong.pangparty.service.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.entity.member.Friendship;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.repository.member.FriendshipRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final MemberRepository memberRepository;
    private final FriendshipRepository friendshipRepository;


    @Override
    @Transactional
    public void addFollow(Long memberUid, String followeeId) {
        Member follower = memberRepository.findMemberByUid(memberUid);
        Member followee = memberRepository.findMemberById(followeeId);
        if (follower == null || followee == null) {
            throw new MemberNotFoundException();
        }

        if (follower.equals(followee)) {
            throw new IllegalArgumentException("자기 자신을 팔로우 할 수 없습니다.");
        }

        Optional<Friendship> friendship = friendshipRepository.findByFollowerAndFollowee(follower.getUid(), followee.getUid());
        if (friendship.isPresent()) {
            throw new IllegalArgumentException("이미 팔로우 관계가 존재합니다.");
        }

        Friendship createFriendship = Friendship.builder()
                .followee(followee)
                .follower(follower)
                .build();

        friendshipRepository.save(createFriendship);
    }

    @Override
    @Transactional
    public void unFollow(Long memberUid, String followeeId) {
        Member follower = memberRepository.findMemberByUid(memberUid);
        Member followee = memberRepository.findMemberById(followeeId);
        if (follower == null || followee == null) {
            throw new MemberNotFoundException();
        }

        if (follower.equals(followee)) {
            throw new IllegalArgumentException("자기 자신을 언팔로우 할 수 없습니다.");
        }

        Optional<Friendship> friendship = friendshipRepository.findByFollowerAndFollowee(follower.getUid(), followee.getUid());
        if (friendship.isEmpty()) {
            throw new IllegalArgumentException("팔로우 관계가 존재하지 않습니다.");
        }

        friendshipRepository.delete(friendship.get());
    }

    @Override
    public List<FriendshipRes> getFollowingList(String memberId, String targetId, Pageable pageable) {
        Member member = memberRepository.findMemberById(memberId);
        Member target = memberRepository.findMemberById(targetId);
        if (member == null || target == null) {
            throw new MemberNotFoundException();
        }

        List<FriendshipRes> followingList = friendshipRepository.findAllByFollower(target.getUid(), pageable);
        if (memberId.equals(targetId)) {
            // 본인이면 팔로우 관계 여부 표시
            for (FriendshipRes friendshipRes : followingList) {
                friendshipRes.setFollowing(true);
            }
        } else {
            List<FriendshipRes> memberFollowingList = friendshipRepository.findAllByFollower(member.getUid());
            // 본인이 아니면 팔로우 관계 여부 표시
            for (FriendshipRes friendshipRes : followingList) {
                if (memberFollowingList.contains(friendshipRes)) {
                    friendshipRes.setFollowing(true);
                }
            }
        }

        return followingList;
    }

    @Override
    public List<FriendshipRes> getFollowerList(String memberId, String targetId, Pageable pageable) {
        Member member = memberRepository.findMemberById(memberId);
        Member target = memberRepository.findMemberById(targetId);
        if (member == null || target == null) {
            throw new MemberNotFoundException();
        }

        List<FriendshipRes> followerList = friendshipRepository.findAllByFollowee(target.getUid(), pageable);
        List<FriendshipRes> memberFollowingList = friendshipRepository.findAllByFollower(member.getUid());

        for (FriendshipRes friendshipRes : followerList) {
            if (memberFollowingList.contains(friendshipRes)) {
                friendshipRes.setFollowing(true);
            }
        }

        return followerList;
    }

    @Override
    public Long getFollowingCount(String memberId) {
        Member member = memberRepository.findMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        return friendshipRepository.countByFollower(member);
    }

    @Override
    public Long getFollowersCount(String memberId) {
        Member member = memberRepository.findMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        return friendshipRepository.countByFollowee(member);
    }
}
