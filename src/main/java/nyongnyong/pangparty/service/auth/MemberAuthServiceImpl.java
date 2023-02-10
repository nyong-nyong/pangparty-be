package nyongnyong.pangparty.service.auth;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.auth.MemberLoginReq;
import nyongnyong.pangparty.dto.auth.MemberRegisterReq;
import nyongnyong.pangparty.entity.auth.MemberAuthInfo;
import nyongnyong.pangparty.entity.auth.Role;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberPersonal;
import nyongnyong.pangparty.entity.member.MemberProfile;
import nyongnyong.pangparty.entity.member.MemberSetting;
import nyongnyong.pangparty.exception.EmailAuthFailExcpetion;
import nyongnyong.pangparty.exception.MemberAlreadyExistsException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.TokenRefreshFailException;
import nyongnyong.pangparty.jwt.JwtTokenProvider;
import nyongnyong.pangparty.repository.auth.MemberAuthInfoRepository;
import nyongnyong.pangparty.repository.member.MemberPersonalRepository;
import nyongnyong.pangparty.repository.member.MemberProfileRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import nyongnyong.pangparty.repository.member.MemberSettingRepository;
import nyongnyong.pangparty.util.RedisUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberAuthServiceImpl implements MemberAuthService {

    private final PasswordEncoder passwordEncoder; // B2CryptPasswordEncoder
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtil redisUtil;

    private final MailService mailService;

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
            throw new MemberAlreadyExistsException();
        }

        // 비밀번호 암호화
        memberRegisterReq.setPassword(passwordEncoder.encode(memberRegisterReq.getPassword()));

        // Member 테이블에 추가
        Member member = fromMemberRegisterReqtoMember(memberRegisterReq);
        memberRepository.save(member);

        // MemberAuth에 추가
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

    @Override
    @Transactional
    public Map<String, String> login(MemberLoginReq memberLoginReq) {
        // email 존재하는지 확인
        MemberAuthInfo memberAuthInfo = memberAuthInfoRepository.findByEmail(memberLoginReq.getEmail());
        if (memberAuthInfo == null) {
            throw new MemberNotFoundException();
        }

        // 비밀번호 맞는지 확인
        if (!passwordEncoder.matches(memberLoginReq.getPassword(), memberAuthInfo.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // TODO 유저 로그인 로그 남겨야지...
        // TODO 이걸 이렇게 두번에 나눠서 넣는 게 맞는 걸까?
        Member member = memberRepository.findByEmail(memberLoginReq.getEmail());
        if (member == null) {
            throw new MemberNotFoundException();
        }

        String accessToken = jwtTokenProvider.generateToken(member.getEmail(), member.getMemberProfile().getId(), member.getUid(), memberAuthInfo.getAuthorities());
        String refreshToken = jwtTokenProvider.generateRefreshToken();

        redisUtil.deleteValue(memberLoginReq.getEmail());
        redisUtil.setValueWithExpiration(memberLoginReq.getEmail(), refreshToken, jwtTokenProvider.refreshTokenExpiration);

        return Map.of("accessToken", "Bearer " + accessToken,
                "refreshToken", "Bearer " + refreshToken);
    }

    @Override
    public void logout(String email) {
        redisUtil.deleteValue(email);
    }

    @Override
    public Map<String, String> getRefreshToken(String email, String refreshToken) {
        MemberAuthInfo memberAuthInfo = memberAuthInfoRepository.findByEmail(email);
        List<Role> roles = memberAuthInfo.getRoles();

        if (redisUtil.getValue(email).equals(refreshToken) && jwtTokenProvider.validateToken(refreshToken)) {
            redisUtil.deleteValue(email);

            String accessToken = jwtTokenProvider.generateToken(email, memberAuthInfo.getMember().getMemberProfile().getId(), memberAuthInfo.getMember().getUid(), roles);
            String newRefreshToken = jwtTokenProvider.generateRefreshToken();

            redisUtil.setValueWithExpiration(email, newRefreshToken, jwtTokenProvider.refreshTokenExpiration);

            return Map.of("accessToken", "Bearer " + accessToken,
                    "refreshToken", "Bearer " + newRefreshToken);
        } else {
            throw new TokenRefreshFailException();
        }
    }

    @Override
    public void sendAuthEmail(String email) {
        String authCode = mailService.getKey(6);
        redisUtil.setValueWithExpiration(email, authCode, 60 * 5);
        mailService.sendMail(email, "팡파레 이메일 인증번호입니다.", "인증번호 : " + authCode);
    }

    @Override
    public void confirmAuthEmail(String email, String key) {
        if (redisUtil.getValue(email).equals(key)) {
            redisUtil.deleteValue(email);
        } else {
            throw new EmailAuthFailExcpetion("인증번호가 일치하지 않습니다.");
        }
    }

    @Override
    public Long getMemberUid(String token) {
        String jwtToken = jwtTokenProvider.resolveToken(token);

        if (jwtTokenProvider.validateToken(jwtToken)) {
            String email = jwtTokenProvider.getEmailFromToken(jwtToken);
            Member member = memberRepository.findByEmail(email);

            if (member == null) {
                return null;
            }

            return member.getUid();
        }

        return null;
    }

    Member fromMemberRegisterReqtoMember(MemberRegisterReq memberRegisterReq) {
        return Member.builder().email(memberRegisterReq.getEmail()).isSocial(false).build();
    }

    MemberAuthInfo fromMemberRegisterReqtoMemberAuthInfo(Member member, MemberRegisterReq memberRegisterReq) {
        return MemberAuthInfo.builder().member(member).email(memberRegisterReq.getEmail()).password(memberRegisterReq.getPassword()).roles(Collections.singletonList(Role.ROLE_USER))
                .build();
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
