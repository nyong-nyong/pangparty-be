package nyongnyong.pangparty.service.member;

import nyongnyong.pangparty.dto.member.FriendshipRes;

import java.awt.print.Pageable;
import java.util.List;

public interface FollowService {
    void addFollow(Long memberUid, String followeeId);

    void unFollow(Long memberUid, String followeeId);

    List<FriendshipRes> getFollowingList(String memberId, String targetId, Pageable pageable);

    List<FriendshipRes> getFollowerList(String memberId, String targetId, Pageable pageable);

    Long getFollowingCount(String memberId);

    Long getFollowersCount(String memberId);
}
