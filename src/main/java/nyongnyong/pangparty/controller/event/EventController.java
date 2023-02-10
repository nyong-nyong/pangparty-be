package nyongnyong.pangparty.controller.event;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.event.EventCreateReq;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.event.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final MemberAuthService memberAuthService;

    @GetMapping("/{eventUid}")
    public ResponseEntity<?> findEventIntroduceByEventUid(@RequestHeader(value = "Authorization") String token, @PathVariable Long eventUid){
        Long memberUid = memberAuthService.getMemberUid(token);   // Test: 31L -> isLiked가 true, 그 외 -> isLiked가 false
        return ResponseEntity.ok(eventService.findEventIntroduceByEventUid(memberUid, eventUid));
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestHeader(value = "Authorization") String token, @RequestBody EventCreateReq eventCreateReq){

        try{
            Long hostUid = memberAuthService.getMemberUid(token);

            // TODO: eventHashtagService에서 addEventHashtag, hashtagService에서 addHashtag 필요. addHashtag에서는 해당 이름의 해시태그 있는지 확인 후 없으면 넣는다.
            Long eventUid = eventService.addEventAndEventTarget(hostUid, eventCreateReq);

            eventCreateReq.setEventUid(eventUid);
            return ResponseEntity.ok(eventUid);
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
