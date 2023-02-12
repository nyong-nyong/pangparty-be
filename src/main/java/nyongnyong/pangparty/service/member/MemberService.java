package nyongnyong.pangparty.service.member;

import nyongnyong.pangparty.dto.member.MemberProfileReq;
import nyongnyong.pangparty.dto.member.MemberProfileRes;

public interface MemberService {
//    boolean isExistMemberByMemberId(String memberId);

    MemberProfileRes findMemberProfile(String memberId, String targetId);

    void updateMemberProfile(String memberId, MemberProfileReq memberProfileReq);
}
