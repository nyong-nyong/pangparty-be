package nyongnyong.pangparty.service.badge;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.badge.MemberBadgeRes;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.repository.badge.BadgeRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final MemberRepository memberRepository;
    private final BadgeRepository badgeRepository;

    @Override
    @Transactional
    public List<MemberBadgeRes> getMemberBadgeList(String memberId) {
        Member member = memberRepository.findMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        List<MemberBadgeRes> memberBadgeResList = badgeRepository.getBadgeListAsMemberBadgeRes(member.getUid());
        for (MemberBadgeRes memberBadgeRes : memberBadgeResList) {
            if (memberBadgeRes.getAcquireTime() != null) {
                memberBadgeRes.setHasBadge(true);
            }
        }

        return memberBadgeResList;
    }
}
