package nyongnyong.pangparty.controller.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.TokenInvalidException;
import nyongnyong.pangparty.jwt.JwtTokenProvider;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.member.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final MemberAuthService memberAuthService;
    private final FollowService followService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/following/{followeeId}")
    public ResponseEntity<?> addFollow(@RequestHeader("Authorization") String token,
                                       @PathVariable String followeeId) {
        try {
            Long memberUid = memberAuthService.getMemberUid(token);

            followService.addFollow(memberUid, followeeId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (TokenInvalidException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/following/{followeeId}")
    public ResponseEntity<?> unFollow(@RequestHeader("Authorization") String token,
                                      @PathVariable String followeeId) {
        try {
            Long memberUid = memberAuthService.getMemberUid(token);

            followService.unFollow(memberUid, followeeId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TokenInvalidException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/following/{targetId}")
    public ResponseEntity<?> getFollowingList(@RequestHeader(required = false, value = "Authorization") String token,
                                              @PathVariable String targetId,
                                              Pageable pageable) {
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            String memberId = jwtTokenProvider.getIdFromToken(token);
            return ResponseEntity.ok(followService.getFollowingList(memberId, targetId, pageable));
        } catch (TokenInvalidException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/followers/{targetId}")
    public ResponseEntity<?> getFollowerList(@RequestHeader(required = false, value = "Authorization") String token,
                                             @PathVariable String targetId,
                                             Pageable pageable) {
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            String memberId = jwtTokenProvider.getIdFromToken(token);
            return ResponseEntity.ok(followService.getFollowerList(memberId, targetId, pageable));
        } catch (TokenInvalidException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
