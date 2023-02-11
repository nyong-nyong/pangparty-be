package nyongnyong.pangparty.service.member;

import nyongnyong.pangparty.dto.member.FriendshipRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowService {
    void addFollow(String memberId, String followeeId);

    void unFollow(String memberId, String followeeId);

    /**
     * 타겟의 팔로잉 리스트 반환
     * @param memberId 로그인한 유저의 아이디
     * @param targetId 타겟의 아이디
     * @param pageable 페이징
     * @return 타겟의 팔로잉 리스트
     */
    Page<FriendshipRes> getFollowingList(String memberId, String targetId, Pageable pageable);

    /**
     * 타겟의 팔로워 리스트 반환
     * @param memberId 로그인한 유저의 아이디
     * @param targetId 타겟의 아이디
     * @param pageable 페이징
     * @return 타겟의 팔로워 리스트
     */
    Page<FriendshipRes> getFollowerList(String memberId, String targetId, Pageable pageable);

    Long getFollowingCount(String memberId);

    Long getFollowersCount(String memberId);
}
