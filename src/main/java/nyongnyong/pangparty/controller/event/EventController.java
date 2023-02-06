package nyongnyong.pangparty.controller.event;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.service.event.EventService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<?> findEventList(Pageable pageable){
        return ResponseEntity.ok(eventService.findEvents(pageable));
    }

}
