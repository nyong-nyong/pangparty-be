package nyongnyong.pangparty.controller.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.dto.feed.FeedRes;
import nyongnyong.pangparty.dto.member.MemberProfilePictureSimpleRes;
import nyongnyong.pangparty.dto.member.MemberProfileReq;
import nyongnyong.pangparty.exception.FeedNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.TokenInvalidException;
import nyongnyong.pangparty.jwt.JwtTokenProvider;
import nyongnyong.pangparty.service.album.MediaService;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.badge.BadgeService;
import nyongnyong.pangparty.service.event.EventService;
import nyongnyong.pangparty.service.feed.PostService;
import nyongnyong.pangparty.service.member.MemberService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final BadgeService badgeService;
    private final EventService eventService;
    private final PostService postService;
    private final MemberService memberService;
    private final MemberAuthService memberAuthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MediaService mediaService;


    @GetMapping(value = {"/profile/{targetId}", "/{targetId}/profile"})
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
                                                 @RequestBody @Valid MemberProfileReq memberProfileReq) {
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

    @PostMapping("/profile/picture")
    public ResponseEntity<?> createMemberProfilePicture(@RequestHeader("Authorization") String token,
                                                        @RequestPart(value = "file", required = false) MultipartFile file) {
        // Validate Token
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Validate Request Part
        if (file == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Long memberUid = memberAuthService.getMemberUid(token);
            String fileName = memberUid.toString().concat(file.getOriginalFilename());
            String contentType = Files.probeContentType(Path.of(fileName));
            if (contentType.startsWith("image")) {  // image
//                MultipartFile webpMedia = mediaService.reformatMedia(file);
//                MultipartFile thumbnail = mediaService.resizeMediaToThumbnail(webpMedia);
//                MultipartFile media = mediaService.resizeMediaToAlbumSize(webpMedia);
//                String originalUrl = mediaService.uploadMedia(webpMedia, "webp/"+fileName, contentType, webpMedia.getSize());
//                String thumbnailUrl = mediaService.uploadMedia(thumbnail, "thumbnail/"+fileName, contentType, thumbnail.getSize());
//                String mediaUrl = mediaService.uploadMedia(media, "album/"+fileName, contentType, media.getSize());
                String profileUrl = mediaService.uploadMedia(file, "profile/"+fileName, contentType, file.getSize());
                MemberProfilePictureSimpleRes memberProfilePictureSimpleRes = memberService.createMemberProfilePicture(memberUid, profileUrl);
                return new ResponseEntity<>(memberProfilePictureSimpleRes, HttpStatus.CREATED);
            } else{
                throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
            }
        } catch (FileSizeLimitExceededException e){
            return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
        } catch (IllegalArgumentException | IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{memberId}/received-events")
    public ResponseEntity<?> findReceivedEvent(@PathVariable("memberId") String memberId) {

        try {
            // Validate Path Variable
            if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();
            List<EventCard> receivedEvents = eventService.findReceivedEventsByMemberId(memberId);
            response.put("totalCnt", receivedEvents.size());
            response.put("receivedEvents", receivedEvents);

            return ResponseEntity.ok(response);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (TokenInvalidException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{memberId}/host-events")
    public ResponseEntity<?> findHostEvent(@PathVariable("memberId") String memberId) {

        try {
            // Validate Path Variable
            if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();
            List<EventCard> hostEvents = eventService.findHostEventsByMemberId(memberId);
            response.put("totalCnt", hostEvents.size());
            response.put("hostEvents", hostEvents);

            return ResponseEntity.ok(response);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (TokenInvalidException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{memberId}/involving-events")
    public ResponseEntity<?> findInvolvingEvent(@PathVariable("memberId") String memberId) {
        try {
            // Validate Path Variable
            if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();
            List<EventCard> involvingEvents = eventService.findInvolvingEventsByMemberId(memberId);
            response.put("totalCnt", involvingEvents.size());
            response.put("involvingEvents", involvingEvents);

            return ResponseEntity.ok(response);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (TokenInvalidException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{memberId}/involved-events")
    public ResponseEntity<?> findInvolvedEvent(@PathVariable("memberId") String memberId) {

        try {
            // Validate Path Variable
            if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();
            List<EventCard> involvedEvents = eventService.findInvolvedEventsByMemberId(memberId);
            response.put("totalCnt", involvedEvents.size());
            response.put("involvedEvents", involvedEvents);

            return ResponseEntity.ok(response);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (TokenInvalidException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{memberId}/feed")
    public ResponseEntity<?> findProfileFeed(@RequestHeader(value = "Authorization") String token, @PathVariable("memberId") String memberId, Pageable pageable) {
        if (pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
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
            } else {
                System.out.println("token is null");
                return ResponseEntity.badRequest().build();
            }

        } catch (FeedNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("feed", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok(response);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (TokenInvalidException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = {"/{memberId}/badges", "/badges/{memberId}"})
    public ResponseEntity<?> findProfileBadges(@PathVariable("memberId") String memberId) {
        // Validate Path Variable
        if (memberId == null || memberId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> response = Map.of("memberBadges", badgeService.getMemberBadgeList(memberId));

        return ResponseEntity.ok(response);
    }
}
