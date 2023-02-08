package nyongnyong.pangparty.service.auth;

import nyongnyong.pangparty.dto.auth.MemberRegisterReq;

public interface MemberAuthService {

    /**
     * 회원 가입
     * @param memberRegisterReq 회원 가입 정보
     * @return MemberUid
     */
    Long register(MemberRegisterReq memberRegisterReq);

}