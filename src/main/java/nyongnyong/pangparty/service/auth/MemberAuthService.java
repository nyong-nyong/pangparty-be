package nyongnyong.pangparty.service.auth;

import nyongnyong.pangparty.dto.auth.MemberLoginReq;
import nyongnyong.pangparty.dto.auth.MemberRegisterReq;

import java.util.Map;

public interface MemberAuthService {

    /**
     * 회원 가입
     *
     * @param memberRegisterReq 회원 가입 정보
     * @return MemberUid
     */
    Long register(MemberRegisterReq memberRegisterReq);


    /**
     * 로그인
     *
     * @param memberLoginReq 로그인 정보
     * @return tokens
     */
    Map<String, String> login(MemberLoginReq memberLoginReq);

    /**
     * 로그아웃
     *
     * @param email 로그아웃할 회원의 이메일
     */
    void logout(String email);

    /**
     * 토큰 갱신
     *
     * @param refreshToken 갱신할 토큰
     * @return token
     */
    Map<String, String> getRefreshToken(String email, String refreshToken);
    
    // TODO 회원 탈퇴


    void sendAuthEmail(String email);

    void confirmAuthEmail(String email, String key);
    
    // TODO 비밀번호 변경

    /**
     * 토큰으로 memberUid 가져오기
     *
     * @param token access 토큰
     * @return memberUid
     */
    Long getMemberUid(String token);

    /**
     * 이메일 중복 체크
     * @param email 이메일
     * @return 중복 여부
     */
    boolean checkExistingEmail(String email);

    /**
     * 아이디 중복 체크
     * @param id 아이디
     * @return 중복 여부
     */
    boolean checkExistingId(String id);

}