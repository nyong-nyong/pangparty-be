package nyongnyong.pangparty.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.member.MemberProfileSimpleRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.member.MemberProfile;
import nyongnyong.pangparty.entity.member.QMemberProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.selectFrom;
import static nyongnyong.pangparty.entity.member.QMemberProfile.memberProfile;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberProfileRepositoryImpl implements MemberProfileRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberProfile> searchMember(SearchReq conditions, Pageable pageable) {

        List<MemberProfile> memberProfiles
                = queryFactory
                .selectFrom(memberProfile)
                .where(memberProfile.id.contains(conditions.getKeyword())
                    .or(memberProfile.name.contains(conditions.getKeyword())))
                .fetch();

        return new PageImpl<>(memberProfiles, pageable, memberProfiles.size());
    }

}
