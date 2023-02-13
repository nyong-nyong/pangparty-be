package nyongnyong.pangparty.service.badge;

import nyongnyong.pangparty.dto.badge.BadgeRes;
import nyongnyong.pangparty.dto.badge.MemberBadgeRes;

import java.util.List;

public interface BadgeService {

    List<BadgeRes> getBadgeList();
    List<MemberBadgeRes> getMemberBadgeList(String memberId);
}
