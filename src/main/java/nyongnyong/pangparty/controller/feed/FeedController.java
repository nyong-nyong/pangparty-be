package nyongnyong.pangparty.controller.feed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.feed.PostCommentReq;
import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.exception.CommentNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.PostNotFoundException;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.feed.FeedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class FeedController {

    private final FeedService feedService;
    private final MemberAuthService memberAuthService;

    @GetMapping("/{postUid}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long postUid,
                                         @RequestParam(required = false, defaultValue = "recent") String type,
                                         Pageable pageable) {
        try {
            System.out.println("type: " + type);
            if (type.equals("recent")) {
                Page<PostCommentRes> postCommentResPage = feedService.getRecentCommentList(postUid, 5);

                Map<String, Object> response = Map.of("size", 5,
                        "itemCnt", postCommentResPage.getTotalElements(),
                        "comments", postCommentResPage.getContent());

                return ResponseEntity.ok(response);
            } else {
                Page<PostCommentRes> postCommentResPage = feedService.getPostCommentList(postUid, pageable);

                Map<String, Object> response = Map.of("size", pageable.getPageSize(),
                        "page", pageable.getPageNumber(),
                        "itemCnt", postCommentResPage.getTotalElements(),
                        "totalPageCnt", postCommentResPage.getTotalPages(),
                        "comments", postCommentResPage.getContent());

                return ResponseEntity.ok(response);
            }
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{postUid}/comments")
    public ResponseEntity<?> addComment(@RequestHeader("Authorization") String token,
                                        @PathVariable Long postUid,
                                        @RequestBody PostCommentReq postCommentReq) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Long memberUid = memberAuthService.getMemberUid(token);

            Long commentUid = feedService.createPostComment(postUid, memberUid, postCommentReq.getContent());

            return ResponseEntity.created(URI.create("/posts/" + postUid + "/comments/" + commentUid)).build();
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{postUid}/comments/{commentUid}")
    public ResponseEntity<?> deleteComment(@RequestHeader("Authorization") String token,
                                           @PathVariable Long postUid,
                                           @PathVariable Long commentUid) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Long memberUid = memberAuthService.getMemberUid(token);

            feedService.deletePostComment(postUid, commentUid, memberUid);

            return ResponseEntity.noContent().build();
        } catch (PostNotFoundException | CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MemberNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
