package nyongnyong.pangparty.controller.feed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.feed.PostCommentReq;
import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.dto.feed.PostReq;
import nyongnyong.pangparty.exception.TokenInvalidException;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.feed.PostCommentService;
import nyongnyong.pangparty.service.feed.PostLikeService;
import nyongnyong.pangparty.service.feed.PostService;
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
public class PostController {

    private final PostService postService;
    private final PostCommentService postCommentService;
    private final PostLikeService postLikeService;
    private final MemberAuthService memberAuthService;

    @PostMapping
    public ResponseEntity<?> addPost(@RequestHeader("Authorization") String token,
                                     @RequestBody PostReq postReq) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long postUid = postService.addPost(postReq, memberAuthService.getMemberUid(token));
        return ResponseEntity.created(URI.create("/posts/" + postUid)).build();
    }

    @GetMapping("/{postUid}")
    public ResponseEntity<?> getPost(@RequestHeader(value = "Authorization", required = false) String token,
                                     @PathVariable Long postUid) {
        if (token != null && !token.isEmpty()) {
            Long memberUid = memberAuthService.getMemberUid(token);
            return ResponseEntity.ok(postService.getPost(postUid, memberUid));
        }

        return ResponseEntity.ok(postService.getPost(postUid, null));

    }

    @DeleteMapping("/{postUid}")
    public ResponseEntity<?> deletePost(@RequestHeader("Authorization") String token,
                                        @PathVariable Long postUid) {
        if (token == null || token.isEmpty()) {
            throw new TokenInvalidException();
        }

        Long memberUid = memberAuthService.getMemberUid(token);
        postService.deletePost(postUid, memberUid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{postUid}")
    public ResponseEntity<?> updatePost(@RequestHeader("Authorization") String token,
                                        @PathVariable Long postUid,
                                        @RequestBody PostReq postReq) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long memberUid = memberAuthService.getMemberUid(token);
        postService.updatePost(postUid, memberUid, postReq);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postUid}/likes")
    public ResponseEntity<?> addLike(@RequestHeader("Authorization") String token,
                                     @PathVariable Long postUid) {
        System.out.println("AddLike");
        System.out.println(token);
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long memberUid = memberAuthService.getMemberUid(token);
        System.out.println("memberUid: " + memberUid);
        postLikeService.addPostLike(memberUid, postUid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{postUid}/likes")
    public ResponseEntity<?> deleteLike(@RequestHeader("Authorization") String token,
                                        @PathVariable Long postUid) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long memberUid = memberAuthService.getMemberUid(token);
        postLikeService.deletePostLike(memberUid, postUid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{postUid}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long postUid,
                                         @RequestParam(required = false, defaultValue = "recent") String type,
                                         Pageable pageable) {
        if (type.equals("recent")) {
            Page<PostCommentRes> postCommentResPage = postCommentService.getRecentCommentList(postUid, 5);

            Map<String, Object> response = Map.of("size", 5,
                    "itemCnt", postCommentResPage.getTotalElements(),
                    "comments", postCommentResPage.getContent());

            return ResponseEntity.ok(response);
        } else {
            Page<PostCommentRes> postCommentResPage = postCommentService.getPostCommentList(postUid, pageable);

            Map<String, Object> response = Map.of("size", pageable.getPageSize(),
                    "page", pageable.getPageNumber(),
                    "itemCnt", postCommentResPage.getTotalElements(),
                    "totalPageCnt", postCommentResPage.getTotalPages(),
                    "comments", postCommentResPage.getContent());

            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/{postUid}/comments")
    public ResponseEntity<?> addComment(@RequestHeader("Authorization") String token,
                                        @PathVariable Long postUid,
                                        @RequestBody PostCommentReq postCommentReq) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long memberUid = memberAuthService.getMemberUid(token);
        Long commentUid = postCommentService.createPostComment(postUid, memberUid, postCommentReq.getContent());

        return ResponseEntity.created(URI.create("/posts/" + postUid + "/comments/" + commentUid)).build();
    }

    @DeleteMapping("/{postUid}/comments/{commentUid}")
    public ResponseEntity<?> deleteComment(@RequestHeader("Authorization") String token,
                                           @PathVariable Long postUid,
                                           @PathVariable Long commentUid) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long memberUid = memberAuthService.getMemberUid(token);
        postCommentService.deletePostComment(postUid, commentUid, memberUid);

        return ResponseEntity.noContent().build();
    }
}
