package nyongnyong.pangparty.controller.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.dto.feed.FeedRes;
import nyongnyong.pangparty.dto.member.MemberProfileReq;
import nyongnyong.pangparty.exception.FeedNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.TokenInvalidException;
import nyongnyong.pangparty.jwt.JwtTokenProvider;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.event.EventService;
import nyongnyong.pangparty.service.feed.PostService;
import nyongnyong.pangparty.service.member.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final EventService eventService;
    private final PostService postService;
    private final MemberService memberService;
    private final MemberAuthService memberAuthService;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping("/profile/{targetId}")
    public ResponseEntity<?> findMemberProfile(@RequestHeader(required = false, value = "Authorization") String token,
                                               @PathVariable("targetId") String targetId) {
        // 로그인 안 한 상태
        if (token == null || token.isEmpty()) {
            try {
                return ResponseEntity.ok(memberService.findMemberProfile(null, targetId));
            } catch (MemberNotFoundException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        // 로그인 상태
        try {
            String memberId = jwtTokenProvider.getIdFromToken(jwtTokenProvider.resolveToken(token));

            return ResponseEntity.ok(memberService.findMemberProfile(memberId, targetId));
        } catch (MemberNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateMemberProfile(@RequestHeader("Authorization") String token,
                                                 @RequestBody @Valid MemberProfileReq memberProfileReq)
    {
        // Validate Token
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Validate Request Body
        if (memberProfileReq == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            String memberId = jwtTokenProvider.getIdFromToken(jwtTokenProvider.resolveToken(token));

            memberService.updateMemberProfile(memberId, memberProfileReq);

            return ResponseEntity.ok().build();
        } catch (MemberNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{memberId}/received-events")
    public ResponseEntity<?> findReceivedEvent(@PathVariable("memberId") String memberId){

        try{
            // Validate Path Variable
            if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();
            List<EventCard> receivedEvents = eventService.findReceivedEventsByMemberId(memberId);
            response.put("totalCnt", receivedEvents.size());
            response.put("receivedEvents", receivedEvents);

            return ResponseEntity.ok(response);
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

    @GetMapping("/{memberId}/host-events")
    public ResponseEntity<?> findHostEvent(@PathVariable("memberId") String memberId){

        try{
            // Validate Path Variable
            if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();
            List<EventCard> hostEvents = eventService.findHostEventsByMemberId(memberId);
            response.put("totalCnt", hostEvents.size());
            response.put("hostEvents", hostEvents);

            return ResponseEntity.ok(response);
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

    @GetMapping("/{memberId}/involving-events")
    public ResponseEntity<?> findInvolvingEvent(@PathVariable("memberId") String memberId){
        try{
            // Validate Path Variable
            if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();
            List<EventCard> involvingEvents = eventService.findInvolvingEventsByMemberId(memberId);
            response.put("totalCnt", involvingEvents.size());
            response.put("involvingEvents", involvingEvents);

            return ResponseEntity.ok(response);
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

    @GetMapping("/{memberId}/involved-events")
    public ResponseEntity<?> findInvolvedEvent(@PathVariable("memberId") String memberId){

        try{
            // Validate Path Variable
            if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();
            List<EventCard> involvedEvents = eventService.findInvolvedEventsByMemberId(memberId);
            response.put("totalCnt", involvedEvents.size());
            response.put("involvedEvents", involvedEvents);

            return ResponseEntity.ok(response);
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

    @GetMapping("/{memberId}/feed")
    public ResponseEntity<?> findProfileFeed(@RequestHeader(value = "Authorization") String token, @PathVariable("memberId") String memberId, Pageable pageable) {
        if (pageable.getPageNumber() < 0 || pageable.getPageSize() < 0){
            return ResponseEntity.badRequest().build();
        }

        try {
            if (token != null && !token.isEmpty()) {
                Long memberUid = memberAuthService.getMemberUid(token);
                Map<String, Object> response = new HashMap<>();
                Page<FeedRes> feed = postService.findProfileFeed(memberUid, memberId, pageable);
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

        } catch (FeedNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("feed", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok(response);
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
