package nyongnyong.pangparty.service.member;

import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.dto.member.MemberProfileReq;
import nyongnyong.pangparty.dto.member.MemberProfileRes;
import nyongnyong.pangparty.dto.member.MemberProfileSimpleRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
//    boolean isExistMemberByMemberId(String memberId);

    MemberProfileRes findMemberProfile(String memberId, String targetId);

    void updateMemberProfile(String memberId, MemberProfileReq memberProfileReq);

    Page<MemberProfileSimpleRes> searchMember(SearchReq conditions, Pageable pageable);
}
