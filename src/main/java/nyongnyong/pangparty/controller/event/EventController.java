package nyongnyong.pangparty.controller.event;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.event.EventCreateReq;
import nyongnyong.pangparty.dto.event.EventCreateRes;
import nyongnyong.pangparty.dto.event.SimpleHashtagName;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.event.EventHashtagService;
import nyongnyong.pangparty.service.event.EventService;
import nyongnyong.pangparty.service.hashtag.HashtagService;
import nyongnyong.pangparty.service.rollingpaper.RollingPaperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final MemberAuthService memberAuthService;
    private final HashtagService hashtagService;
    private final EventHashtagService eventHashtagService;

    @GetMapping("/{eventUid}")
    public ResponseEntity<?> findEventIntroduceByEventUid(@RequestHeader(value = "Authorization") String token, @PathVariable Long eventUid){
        Long memberUid = memberAuthService.getMemberUid(token);   // Test: 31L -> isLiked가 true, 그 외 -> isLiked가 false
//        Long rollingPaperUid = eventService.getEventByEventUid(eventUid).getRollingPaper().getUid();
        return ResponseEntity.ok(eventService.findEventIntroduceByEventUid(memberUid, eventUid));
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestHeader(value = "Authorization") String token, @RequestBody EventCreateReq eventCreateReq){

        try{
            Long hostUid = memberAuthService.getMemberUid(token);

            // TODO: 대표사진 s3 업로드 로직 필요...

            Event event = eventService.addEventAndEventTarget(hostUid, eventCreateReq);
            Long eventUid = event.getUid();

            // TODO: eventHashtagService에서 addEventHashtag, hashtagService에서 addHashtag 필요. addHashtag에서는 해당 이름의 해시태그 있는지 확인 후 없으면 넣는다.
            for(SimpleHashtagName hashtag : eventCreateReq.getHashtags()){
                SimpleHashtagName simpleHashtagName = hashtag;
                Hashtag simpleHashtag = hashtagService.addHashtagIfHashtagNameExists(simpleHashtagName.getName());  // 사용자가 작성한 해시태그가 존재하는지 확인, 없으면 hashtag 테이블에 추가
                eventHashtagService.addEventHashtag(event, simpleHashtag);      // hashtagName에 해당하는 hashtagUid를 가져와서 eventHashtag 테이블에 추가
            }

            // rollingPaperRepository에서 save
            eventService.addRollingPaper(eventUid);

            // TODO: albumRepository에서 save

            eventCreateReq.setEventUid(eventUid);
            EventCreateRes eventCreateRes = new EventCreateRes(eventUid);
            return ResponseEntity.created(URI.create("/events/"+eventUid)).body(eventCreateRes);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{eventUid}/like")
    public ResponseEntity<?> likeEvent(@PathVariable Long eventUid){

        // Validate Path Variable and Request Body




//        Long memberUid = 31L;


        return null;
    }

//    @GetMapping
//    public ResponseEntity<?> findEventList(Pageable pageable){
//        return ResponseEntity.ok(eventService.findEvents(pageable));
//    }

//    @GetMapping
//    public ResponseEntity<?> findHomeEvents(){
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("popularEvents", eventService.findPopularEvents());
//        response.put("createdTodayEvents", eventService.findCreatedTodayEvents());
//        response.put("dDayEvents", eventService.findDDayEvents());
//
//
//        return ResponseEntity.ok(eventService.findHomeEvents());
//    }
}
