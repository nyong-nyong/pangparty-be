package nyongnyong.pangparty.service.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.member.MemberProfileRes;
import nyongnyong.pangparty.entity.member.Friendship;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.member.FriendshipRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;
    private final FriendshipRepository friendshipRepository;

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

//    @Override
//    public boolean isExistMemberByMemberId(String memberId) {
//        return memberRepository.;
//    }



}
