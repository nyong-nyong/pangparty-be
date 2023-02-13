package nyongnyong.pangparty.service.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.dto.member.MemberProfileReq;
import nyongnyong.pangparty.dto.member.MemberProfileRes;
import nyongnyong.pangparty.dto.member.MemberProfileSimpleRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.member.Friendship;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberProfile;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.member.FriendshipRepository;
import nyongnyong.pangparty.repository.member.MemberProfileRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;
    private final FriendshipRepository friendshipRepository;
    private final MemberProfileRepository memberProfileRepository;

    @Override
    @Transactional(readOnly = true)
    public MemberProfileRes findMemberProfile(String memberId, String targetId) {
        Member target = memberRepository.findMemberById(targetId);
        if (target == null) {
            throw new MemberNotFoundException();
        }

        Long followingCount = friendshipRepository.countByFollower(target.getUid());
        Long followerCount = friendshipRepository.countByFollowee(target.getUid());

        Long hostEventCount = eventRepository.countHostEventsByMemberId(targetId);
        Long involvingEventCount = eventRepository.countInvolvingEventsByMemberId(targetId);
        Long involvedEventCount = eventRepository.countInvolvedEventsByMemberId(targetId);

        MemberProfileRes memberProfileRes = new MemberProfileRes().builder()
                .id(targetId)
                .name(target.getMemberProfile().getName())
                .email(target.getEmail())
                .introduction(target.getMemberProfile().getIntroduction())
                .imgUrl(target.getMemberProfile().getImgUrl())
                .followerCount(followerCount)
                .followingCount(followingCount)
                .hostEventCount(hostEventCount)
                .involvingEventCount(involvingEventCount)
                .involvedEventCount(involvedEventCount)
                .build();

        if (memberId != null) {
            Member member = memberRepository.findMemberById(memberId);
            if (member == null) {
                throw new MemberNotFoundException();
            }

            Optional<Friendship> friendship = friendshipRepository.findByFollowerAndFollowee(member.getUid(), target.getUid());
            memberProfileRes.setFollowing(friendship.isPresent());
        }

        return memberProfileRes;
    }

    @Override
    @Transactional
    public void updateMemberProfile(String memberId, MemberProfileReq memberProfileReq) {
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            throw new MemberNotFoundException();
        }

        // id 중복 체크
        if (!memberId.equals(memberProfileReq.getId()) && memberProfileRepository.findByMemberId(memberProfileReq.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        memberProfileRepository.updateMemberProfile(member.getUid(), memberProfileReq.getId(),
                memberProfileReq.getName(), memberProfileReq.getImgUrl(), memberProfileReq.getIntroduction());
    }

    @Override
    public Page<MemberProfileSimpleRes> searchMember(SearchReq conditions, Pageable pageable) {
        return memberProfileRepository.searchMember(conditions, pageable).map(MemberProfileSimpleRes::new);
    }


//    @Override
//    public boolean isExistMemberByMemberId(String memberId) {
//        return memberRepository.;
//    }


}
