package nyongnyong.pangparty.controller.feed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.feed.FeedRes;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.PostNotFoundException;
import nyongnyong.pangparty.exception.TokenInvalidException;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.feed.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedController {
    private final PostService postService;
    private final MemberAuthService memberAuthService;

    @GetMapping
    public ResponseEntity<?> getFeed(@RequestHeader(value = "Authorization") String token, Pageable pageable) {
        if (pageable.getPageNumber() < 0 || pageable.getPageSize() < 0){
            return ResponseEntity.badRequest().build();
        }

        try {
            if (token != null && !token.isEmpty()) {
                Long memberUid = memberAuthService.getMemberUid(token);
                Map<String, Object> response = new HashMap<>();
                Page<FeedRes> feed = postService.getFeed(memberUid, pageable);
                response.put("size", pageable.getPageSize());
                response.put("page", pageable.getPageNumber());
                response.put("itemCnt", feed.getNumberOfElements());
                response.put("totalPageCnt", feed.getTotalPages());
                response.put("feed", feed.getContent());
                return ResponseEntity.ok(response);
            }else{
                System.out.println("token is null");
                return ResponseEntity.badRequest().build();
            }
        } catch (PostNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (MemberNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (TokenInvalidException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
