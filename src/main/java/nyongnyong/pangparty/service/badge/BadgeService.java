package nyongnyong.pangparty.service.badge;

import nyongnyong.pangparty.dto.badge.MemberBadgeRes;

import java.util.List;

public interface BadgeService {
    List<MemberBadgeRes> getMemberBadgeList(String memberId);

    void updateMemberBadge(Long memberUid);
}
