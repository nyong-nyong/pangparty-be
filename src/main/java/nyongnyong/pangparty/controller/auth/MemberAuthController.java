package nyongnyong.pangparty.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.auth.MemberRegisterReq;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberAuthController {

    private final MemberAuthService memberAuthService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login() {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout() {
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody MemberRegisterReq memberRegisterReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }

        try {
            Long uid = memberAuthService.register(memberRegisterReq);
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            log.info("e = {}", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
