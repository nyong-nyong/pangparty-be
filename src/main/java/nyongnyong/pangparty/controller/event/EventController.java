package nyongnyong.pangparty.controller.event;

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
