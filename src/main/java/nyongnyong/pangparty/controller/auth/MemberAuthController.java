package nyongnyong.pangparty.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.auth.*;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> login(@Validated @RequestBody MemberLoginReq memberLoginReq) {
        Map<String, String> response = memberAuthService.login(memberLoginReq);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
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
    public ResponseEntity<?> register(@Validated @RequestBody MemberRegisterReq memberRegisterReq, BindingResult
            bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }

        memberAuthService.register(memberRegisterReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
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
    public ResponseEntity<?> confirmAuthEmail(@RequestBody @Valid EmailAuthReq emailAuthReq, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }

        memberAuthService.confirmAuthEmail(emailAuthReq.getEmail(), emailAuthReq.getAuthCode());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestBody @Valid EmailSimple email, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }

        boolean result = memberAuthService.checkExistingEmail(email.getEmail());

        Map<String, Object> response = Map.of("isExisting", result);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/check-id")
    public ResponseEntity<?> checkId(@RequestBody @Valid IdSimple id, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }

        boolean result = memberAuthService.checkExistingId(id.getId());

        Map<String, Object> response = Map.of("isExisting", result);

        return ResponseEntity.ok(response);
    }
}
