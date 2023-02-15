package nyongnyong.pangparty.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.event.*;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.TokenInvalidException;
import nyongnyong.pangparty.service.album.AlbumService;
import nyongnyong.pangparty.service.album.MediaService;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.event.EventHashtagService;
import nyongnyong.pangparty.service.event.EventParticipantService;
import nyongnyong.pangparty.service.event.EventService;
import nyongnyong.pangparty.service.hashtag.HashtagService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private final EventService eventService;
    private final EventParticipantService eventParticipantService;
    private final MemberAuthService memberAuthService;
    private final HashtagService hashtagService;
    private final EventHashtagService eventHashtagService;
    private final AlbumService albumService;
    private final MediaService mediaService;

    @GetMapping("/{eventUid}")
    public ResponseEntity<?> findEventIntroduceByEventUid(@RequestHeader(value = "Authorization") String token, @PathVariable Long eventUid){
        try{
            Long memberUid = memberAuthService.getMemberUid(token);   // Test: 31L -> isLiked가 true, 그 외 -> isLiked가 false
//        Long rollingPaperUid = eventService.getEventByEventUid(eventUid).getRollingPaper().getUid();
            return ResponseEntity.ok(eventService.findEventIntroduceByEventUid(memberUid, eventUid));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestHeader(value = "Authorization") String token, @RequestBody EventCreateReq eventCreateReq){

        try{
            Long hostUid = memberAuthService.getMemberUid(token);
            if (hostUid == null){
                log.debug("hostUid is null");
                return ResponseEntity.badRequest().build();
            }

            Event event = eventService.addEventAndEventTarget(hostUid, eventCreateReq);
            Long eventUid = event.getUid();

            // eventHashtagService에서 addEventHashtag, hashtagService에서 addHashtag 필요. addHashtag에서는 해당 이름의 해시태그 있는지 확인 후 없으면 넣는다.
            for(SimpleHashtagName hashtag : eventCreateReq.getHashtags()){
                SimpleHashtagName simpleHashtagName = hashtag;
                Hashtag simpleHashtag = hashtagService.addHashtagIfHashtagNameExists(simpleHashtagName.getName());  // 사용자가 작성한 해시태그가 존재하는지 확인, 없으면 hashtag 테이블에 추가
                eventHashtagService.addEventHashtag(event, simpleHashtag);      // hashtagName에 해당하는 hashtagUid를 가져와서 eventHashtag 테이블에 추가
            }

            // rollingPaperRepository에서 save
            eventService.addRollingPaper(eventUid);

            // albumRepository에서 save
            albumService.createAlbum(eventUid);

            eventCreateReq.setEventUid(eventUid);
            EventCreateRes eventCreateRes = new EventCreateRes(eventUid);
            return ResponseEntity.created(URI.create("/events/"+eventUid)).body(eventCreateRes);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{eventUid}/like")
    public ResponseEntity<?> likeEvent(@RequestHeader(value = "Authorization") String token, @PathVariable Long eventUid){
        // Validate Path Variable and Request Body
        if(eventUid == null){
            return ResponseEntity.badRequest().build();
        }
        try{
            Long memberUid = memberAuthService.getMemberUid(token);
            eventService.likeEvent(memberUid, eventUid);
            return ResponseEntity.created(URI.create("/events/"+eventUid+"/like")).build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{eventUid}/like")
    public ResponseEntity<?> dislikeEvent(@RequestHeader(value = "Authorization") String token, @PathVariable Long eventUid){
            // Validate Path Variable and Request Body
            log.debug("dislikeEvent");
            if(eventUid == null){
                log.debug("eventUid is null");
                return ResponseEntity.badRequest().build();
            }
            try{
                System.out.println("eventUid: " + eventUid);
                Long memberUid = memberAuthService.getMemberUid(token);
                log.debug("memberUid: " + memberUid);
                eventService.dislikeEvent(memberUid, eventUid);
                return ResponseEntity.noContent().build();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
    }

    /**
     * 이벤트 헤더 이미지 업로드
     * @param token
     * @param eventUid
     * @param file
     * @return
     */
    @PostMapping("/{eventUid}/header")
    public ResponseEntity<?> createEventHeader(@RequestHeader(value = "Authorization") String token, @PathVariable Long eventUid, @RequestPart(value = "file", required = false) MultipartFile file){
        try {
            Long memberUid = memberAuthService.getMemberUid(token);
            String fileName = file.getOriginalFilename();
            if (fileName == null) {
                log.debug("fileName is null");
                return ResponseEntity.badRequest().build();
            }
            log.debug("fileName: " + fileName);
            fileName = String.valueOf(System.currentTimeMillis()).concat(fileName);
            String contentType = Files.probeContentType(Path.of(fileName));
            log.debug("contentType: " + contentType);
            if (contentType.startsWith("image")) {   // image
                String headerUrl = mediaService.uploadMedia(file, "eventHeader/" + fileName, contentType, file.getSize());
                EventHeaderRes eventHeaderRes = eventService.updateThumbnail(eventUid, headerUrl);
                return new ResponseEntity<>(eventHeaderRes, HttpStatus.CREATED);
            }
        }catch (FileSizeLimitExceededException e){
            return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<?> findHomeEvents(@RequestParam String type){
        try{
            // Validate Path Variable
            String[] types = { "start", "end" };
            List<String> typeList = Arrays.asList(types);
            if(!typeList.contains(type)){ // type이 start, end가 아니면 bad request
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> response = new HashMap<>();

            if(type.equals("start")) {
                List<EventCard> todayStartEvents = eventService.findTodayStartEvents();
                response.put("startEvents", todayStartEvents);
            } else if(type.equals("end")){
                List<EventCard> todayEndEvents = eventService.findTodayEndEvents();
                response.put("endEvents", todayEndEvents);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{eventUid}/export")
    public ResponseEntity<?> findExportStatistics(@PathVariable Long eventUid){
        try{
            Map<String, Object> response = new HashMap<>();
            List<EventExportRes> eventExportRes = eventService.findExportStatistics(eventUid);
            response.put("eventExports", eventExportRes);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{eventUid}/export/all")
    public ResponseEntity<Map<String, Object>> findAllExport(@RequestHeader(value = "Authorization") String token, @PathVariable Long eventUid) {
        try {
            Long memberUid = memberAuthService.getMemberUid(token);
            if (eventService.isEventTarget(memberUid, eventUid) || eventService.isEventHost(memberUid, eventUid) || eventParticipantService.isEventParticipant(memberUid, eventUid)) {
                Map<String, Object> response = eventService.findAllExport(eventUid);
                return ResponseEntity.ok(response);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
