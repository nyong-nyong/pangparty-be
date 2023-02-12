package nyongnyong.pangparty.controller.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.TokenInvalidException;
import nyongnyong.pangparty.jwt.JwtTokenProvider;
import nyongnyong.pangparty.service.member.FollowService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/following/{followeeId}")
    public ResponseEntity<?> addFollow(@RequestHeader(required = false, value = "Authorization") String token,
                                       @PathVariable String followeeId) {
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            String memberId = jwtTokenProvider.getIdFromToken(jwtTokenProvider.resolveToken(token));

            followService.addFollow(memberId, followeeId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (TokenInvalidException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/following/{followeeId}")
    public ResponseEntity<?> unFollow(@RequestHeader(required = false, value = "Authorization") String token,
                                      @PathVariable String followeeId) {
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            String memberId = jwtTokenProvider.getIdFromToken(jwtTokenProvider.resolveToken(token));

            followService.unFollow(memberId, followeeId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TokenInvalidException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/followers/{followerId}")
    public ResponseEntity<?> deleteFollower(@RequestHeader(required = false, value = "Authorization") String token,
                                            @PathVariable String followerId) {
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            String memberId = jwtTokenProvider.getIdFromToken(jwtTokenProvider.resolveToken(token));

            followService.unFollow(followerId, memberId);
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
            String memberId = jwtTokenProvider.getIdFromToken(jwtTokenProvider.resolveToken(token));

            Page<FriendshipRes> followingPage = followService.getFollowingList(memberId, targetId, pageable);

            Map<String, Object> response = Map.of("size", pageable.getPageSize(),
                    "page", pageable.getPageNumber(),
                    "itemCnt", followingPage.getTotalElements(),
                    "totalPageCnt", followingPage.getTotalPages(),
                    "following", followingPage.getContent());

            return ResponseEntity.ok(response);
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
            String memberId = jwtTokenProvider.getIdFromToken(jwtTokenProvider.resolveToken(token));

            Page<FriendshipRes> followerPage = followService.getFollowerList(memberId, targetId, pageable);

            Map<String, Object> response = Map.of("size", pageable.getPageSize(),
                    "page", pageable.getPageNumber(),
                    "itemCnt", followerPage.getTotalElements(),
                    "totalPageCnt", followerPage.getTotalPages(),
                    "followers", followerPage.getContent());

            return ResponseEntity.ok(response);
        } catch (TokenInvalidException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
