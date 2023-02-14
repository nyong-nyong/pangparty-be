package nyongnyong.pangparty.service.badge;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.badge.MemberBadgeRes;
import nyongnyong.pangparty.entity.badge.MemberBadge;
import nyongnyong.pangparty.entity.badge.MemberBadgeInfo;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.repository.badge.BadgeRepository;
import nyongnyong.pangparty.repository.badge.MemberBadgeInfoRepository;
import nyongnyong.pangparty.repository.badge.MemberBadgeRepository;
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
    private final MemberBadgeRepository memberBadgeRepository;
    private final MemberBadgeInfoRepository memberBadgeInfoRepository;

    @Override
    @Transactional
    public List<MemberBadgeRes> getMemberBadgeList(String memberId) {
        Member member = memberRepository.findMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        updateMemberBadge(member);

        List<MemberBadgeRes> memberBadgeResList = memberBadgeInfoRepository.getBadgeListAsMemberBadgeRes(member.getUid());
        for (MemberBadgeRes memberBadgeRes : memberBadgeResList) {
            if (memberBadgeRes.getAcquireTime() != null) {
                memberBadgeRes.setHasBadge(true);
            }
        }

        return memberBadgeResList;
    }

    @Override
    @Transactional
    public void updateMemberBadge(Member member) {
        System.out.println("UPDATE MEMBER BADGE");
        MemberBadgeInfo memberBadgeInfo = memberBadgeInfoRepository.findByMemberUid(member.getUid());
        System.out.println(memberBadgeInfo);

        if (memberBadgeInfo == null) {
            memberBadgeInfo = MemberBadgeInfo.builder().member(member).build();
            memberBadgeInfoRepository.save(memberBadgeInfo);
            return;
        }

        List<MemberBadgeRes> memberBadgeResList = memberBadgeInfoRepository.getBadgeListAsMemberBadgeRes(member.getUid());
        for (MemberBadgeRes memberBadgeRes : memberBadgeResList) {
            if (memberBadgeRes.getAcquireTime() == null) {
                switch (memberBadgeRes.getUid().toString()) {
                    case "1":
                        if (memberBadgeInfo.getLoginCount() == 1) {
                            MemberBadge memberBadge = MemberBadge.builder().member(member).badge(badgeRepository.findBadgeByUid(1L)).build();
                            memberBadgeRepository.save(memberBadge);
                        }
                        break;
                    case "2":
                        if (memberBadgeInfo.getParticipateCount() == 1) {
                            MemberBadge memberBadge = MemberBadge.builder().member(member).badge(badgeRepository.findBadgeByUid(2L)).build();
                            memberBadgeRepository.save(memberBadge);
                        }
                        break;
                    case "3":
                        if (memberBadgeInfo.getReceiveCount() == 1) {
                            MemberBadge memberBadge = MemberBadge.builder().member(member).badge(badgeRepository.findBadgeByUid(3L)).build();
                            memberBadgeRepository.save(memberBadge);
                        }
                        break;
                    case "5":
                        if (memberBadgeInfo.getParticipateCount() == 5) {
                            MemberBadge memberBadge = MemberBadge.builder().member(member).badge(badgeRepository.findBadgeByUid(5L)).build();
                            memberBadgeRepository.save(memberBadge);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
