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

}