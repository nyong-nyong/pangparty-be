package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.member.MemberProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberProfileRepositoryCustom {

    Page<MemberProfile> searchMember(SearchReq conditions, Pageable pageable);

}
