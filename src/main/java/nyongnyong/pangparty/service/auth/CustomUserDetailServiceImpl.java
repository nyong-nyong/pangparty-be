package nyongnyong.pangparty.service.auth;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.entity.auth.MemberAuthInfo;
import nyongnyong.pangparty.repository.auth.MemberAuthInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements CustomUserDetailService {

    private final MemberAuthInfoRepository memberAuthInfoRepository;

    @Override // email이 username 역할
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberAuthInfo memberAuthInfo = memberAuthInfoRepository.findByEmail(username);

        if (memberAuthInfo == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return memberAuthInfo;
    }

}
