package nyongnyong.pangparty.service.badge;

import nyongnyong.pangparty.dto.badge.MemberBadgeRes;
import nyongnyong.pangparty.entity.member.Member;

import java.util.List;

public interface BadgeService {
    List<MemberBadgeRes> getMemberBadgeList(String memberId);

    void updateMemberBadge(Member member);
}
