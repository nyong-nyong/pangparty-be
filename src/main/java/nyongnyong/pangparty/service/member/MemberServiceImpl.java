package nyongnyong.pangparty.service.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

//    @Override
//    public boolean isExistMemberByMemberId(String memberId) {
//        return ;
//    }
//
//    @Override
//    public Long findMemberUidByMemberId(String memberId) {
//        return memberRepository.;
//    }
}
