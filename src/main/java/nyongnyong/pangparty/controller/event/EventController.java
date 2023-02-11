package nyongnyong.pangparty.controller.event;

import com.amazonaws.util.json.Jackson;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.event.*;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.event.EventHashtagService;
import nyongnyong.pangparty.service.event.EventService;
import nyongnyong.pangparty.service.hashtag.HashtagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
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
    public ResponseEntity<?> likeEvent(@RequestHeader(value = "Authorization") String token, @PathVariable Long eventUid){
        // Validate Path Variable and Request Body
        if(eventUid == null){
            return ResponseEntity.badRequest().build();
        }
        try{
            Long memberUid = memberAuthService.getMemberUid(token);
            eventService.likeEvent(memberUid, eventUid);
            return ResponseEntity.created(URI.create("/events/"+eventUid+"/like")).build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{eventUid}/dislike")
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
            } catch (Exception e){
                log.debug("dislikeEvent error");
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
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
            }

            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
