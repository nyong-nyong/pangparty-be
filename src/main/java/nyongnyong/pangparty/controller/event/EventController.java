package nyongnyong.pangparty.controller.event;

import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.service.event.EventService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{eventUid}")
    public ResponseEntity<?> findEventIntroduceByEventUid(@PathVariable Long eventUid){
        Long memberUid = 31L;   // Test: 31L -> isLiked가 true, 그 외 -> isLiked가 false
        // TODO: memberUid를 어떻게 받아올지 고민해보기 memberUid = memberAuthService.getMemberUid();
        return ResponseEntity.ok(eventService.findEventIntroduceByEventUid(memberUid, eventUid));
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
