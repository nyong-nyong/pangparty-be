package nyongnyong.pangparty.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.auth.EmailSimple;
import nyongnyong.pangparty.dto.auth.MemberLoginReq;
import nyongnyong.pangparty.dto.auth.MemberRegisterReq;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class MemberAuthController {

    private final MemberAuthService memberAuthService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody MemberLoginReq memberLoginReq, BindingResult bindingResult) {

        Map<String, String> response = memberAuthService.login(memberLoginReq);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
//        System.out.println("LOGOUT========================");
//        String username = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(principal instanceof UserDetails) username = ((UserDetails) principal).getUsername();
//        else{
//            username = principal.toString();
//            System.out.println(username);
//        }
        System.out.println(httpServletRequest.getRemoteUser());
        if (httpServletRequest.getRemoteUser() == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            memberAuthService.logout(httpServletRequest.getRemoteUser());
            httpServletRequest.logout();
        } catch (ServletException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

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

    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest httpServletRequest) {
        String refreshToken = httpServletRequest.getHeader("RefreshToken");
        Map<String, String> response = memberAuthService.getRefreshToken(httpServletRequest.getRemoteUser(), refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth-email")
    public ResponseEntity<?> sendAuthEmail(@RequestBody @Valid EmailSimple email, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }

        memberAuthService.sendAuthEmail(email.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirm-auth-email")
    public ResponseEntity<?> confirmAuthEmail(@RequestBody @Valid EmailSimple email, @RequestBody String authCode, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }

        memberAuthService.confirmAuthEmail(email.getEmail(), authCode);
        return ResponseEntity.ok().build();
    }
}
