package nyongnyong.pangparty.service.auth;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.auth.MemberRegisterReq;
import nyongnyong.pangparty.entity.auth.MemberAuthInfo;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberPersonal;
import nyongnyong.pangparty.entity.member.MemberProfile;
import nyongnyong.pangparty.entity.member.MemberSetting;
import nyongnyong.pangparty.repository.auth.MemberAuthInfoRepository;
import nyongnyong.pangparty.repository.member.MemberPersonalRepository;
import nyongnyong.pangparty.repository.member.MemberProfileRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import nyongnyong.pangparty.repository.member.MemberSettingRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberAuthServiceImpl implements MemberAuthService, UserDetailsService {

    private final PasswordEncoder passwordEncoder; // B2CryptPasswordEncoder

    private final MemberRepository memberRepository;
    private final MemberAuthInfoRepository memberAuthInfoRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final MemberPersonalRepository memberPersonalRepository;
    private final MemberSettingRepository memberSettingRepository;

    @Override
    @Transactional
    public Long register(MemberRegisterReq memberRegisterReq) {
        // check if duplicate email/id
        if (memberRepository.existsByEmail(memberRegisterReq.getEmail()) || memberProfileRepository.existsById(memberRegisterReq.getId())) {
            // TODO 따로 Exception 처리해주기 DuplicateMemberException 등
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        // 비밀번호 암호화
        memberRegisterReq.setPassword(passwordEncoder.encode(memberRegisterReq.getPassword()));

        // Member 테이블에 추가
        Member member = fromMemberRegisterReqtoMember(memberRegisterReq);
        memberRepository.save(member);

        // MemberAuth에 추가
        // TODO 여기서 member UID가 제대로 들어가니...?
        MemberAuthInfo memberAuthInfo = fromMemberRegisterReqtoMemberAuthInfo(member, memberRegisterReq);
        memberAuthInfoRepository.save(memberAuthInfo);

        // 사용자 프로필에 추가
        MemberProfile memberProfile = fromMemberRegisterReqtoMemberProfile(member, memberRegisterReq);
        memberProfileRepository.save(memberProfile);

        // 사용자 개인정보에 추가
        MemberPersonal memberPersonal = fromMemberRegisterReqtoMemberPersonal(member, memberRegisterReq);
        memberPersonalRepository.save(memberPersonal);

        // 사용자 설정에 추가
        MemberSetting memberSetting = fromMemberRegisterReqtoMemberSetting(member);
        memberSettingRepository.save(memberSetting);

        return member.getUid();
    }

    @Override // email이 username 역할
    public MemberAuthInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberAuthInfo memberAuthInfo = memberAuthInfoRepository.findByEmail(username);

        if (memberAuthInfo == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return memberAuthInfo;
    }


    Member fromMemberRegisterReqtoMember(MemberRegisterReq memberRegisterReq) {
        return Member.builder().email(memberRegisterReq.getEmail()).isSocial(false).build();
    }

    MemberAuthInfo fromMemberRegisterReqtoMemberAuthInfo(Member member, MemberRegisterReq memberRegisterReq) {
        return MemberAuthInfo.builder().member(member).email(memberRegisterReq.getEmail()).password(memberRegisterReq.getPassword()).build();
    }

    MemberProfile fromMemberRegisterReqtoMemberProfile(Member member, MemberRegisterReq memberRegisterReq) {
        return MemberProfile.builder().member(member).id(memberRegisterReq.getId()).name(memberRegisterReq.getName()).imgUrl(memberRegisterReq.getImgUrl()).introduction(memberRegisterReq.getIntroduction()).build();
    }

    MemberPersonal fromMemberRegisterReqtoMemberPersonal(Member member, MemberRegisterReq memberRegisterReq) {
        return MemberPersonal.builder().member(member).email(memberRegisterReq.getEmail()).build();
    }

    MemberSetting fromMemberRegisterReqtoMemberSetting(Member member) {
        return MemberSetting.builder().member(member).alarmOnAll(true).build();
    }
}
