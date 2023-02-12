package nyongnyong.pangparty.controller.feed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.feed.PostCommentReq;
import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.dto.feed.PostReq;
import nyongnyong.pangparty.exception.CommentNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.PostNotFoundException;
import nyongnyong.pangparty.service.auth.MemberAuthService;
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
    private final MemberAuthService memberAuthService;

    @PostMapping
    public ResponseEntity<?> addPost(@RequestHeader("Authorization") String token,
                                     @RequestBody PostReq postReq) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Long postUid = postService.addPost(postReq, memberAuthService.getMemberUid(token));
            return ResponseEntity.created(URI.create("/posts/" + postUid)).build();
        } catch (MemberNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{postUid}")
    public ResponseEntity<?> getPost(@RequestHeader(value = "Authorization", required = false) String token,
                                     @PathVariable Long postUid) {
        try {
            if (token != null && !token.isEmpty()) {
                Long memberUid = memberAuthService.getMemberUid(token);
                return ResponseEntity.ok(postService.getPost(postUid, memberUid));
            }

            return ResponseEntity.ok(postService.getPost(postUid, null));
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MemberNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{postUid}")
    public ResponseEntity<?> deletePost(@RequestHeader("Authorization") String token,
                                        @PathVariable Long postUid) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Long memberUid = memberAuthService.getMemberUid(token);
            postService.deletePost(postUid, memberUid);
            return ResponseEntity.noContent().build();
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{postUid}")
    public ResponseEntity<?> updatePost(@RequestHeader("Authorization") String token,
                                        @PathVariable Long postUid,
                                        @RequestBody PostReq postReq) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Long memberUid = memberAuthService.getMemberUid(token);
            postService.updatePost(postUid, memberUid, postReq);
            return ResponseEntity.ok().build();
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{postUid}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long postUid,
                                         @RequestParam(required = false, defaultValue = "recent") String type,
                                         Pageable pageable) {
        try {
            if (type.equals("recent")) {
                Page<PostCommentRes> postCommentResPage = postService.getRecentCommentList(postUid, 5);

                Map<String, Object> response = Map.of("size", 5,
                        "itemCnt", postCommentResPage.getTotalElements(),
                        "comments", postCommentResPage.getContent());

                return ResponseEntity.ok(response);
            } else {
                Page<PostCommentRes> postCommentResPage = postService.getPostCommentList(postUid, pageable);

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

            Long commentUid = postService.createPostComment(postUid, memberUid, postCommentReq.getContent());

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

            postService.deletePostComment(postUid, commentUid, memberUid);

            return ResponseEntity.noContent().build();
        } catch (PostNotFoundException | CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MemberNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
