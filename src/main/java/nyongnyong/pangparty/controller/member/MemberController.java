package nyongnyong.pangparty.controller.member;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.service.event.EventService;
import nyongnyong.pangparty.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final EventService eventService;
    private final MemberService memberService;

    @GetMapping("/{memberId}/received-events")
    public ResponseEntity<?> findReceivedEvent(@PathVariable("memberId") String memberId){
        // Validate Path Variable
        if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> response = new HashMap<>();
        List<EventCard> receivedEvents = eventService.findReceivedEventsByMemberId(memberId);
        response.put("totalCnt", receivedEvents.size());
        response.put("receivedEvents", receivedEvents);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}/host-events")
    public ResponseEntity<?> findHostEvent(@PathVariable("memberId") String memberId){
        // Validate Path Variable
        if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> response = new HashMap<>();
        List<EventCard> hostEvents = eventService.findHostEventsByMemberId(memberId);
        response.put("totalCnt", hostEvents.size());
        response.put("hostEvents", hostEvents);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}/involving-events")
    public ResponseEntity<?> findInvolvingEvent(@PathVariable("memberId") String memberId){
        // Validate Path Variable
        if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> response = new HashMap<>();
        List<EventCard> involvingEvents = eventService.findInvolvingEventsByMemberId(memberId);
        response.put("totalCnt", involvingEvents.size());
        response.put("involvingEvents", involvingEvents);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}/involved-events")
    public ResponseEntity<?> findInvolvedEvent(@PathVariable("memberId") String memberId){
        // Validate Path Variable
        if (memberId == null || memberId.isEmpty()) {   // DB에 있는 id인지 레포지토리에서 검사도 해야할듯.. 근데 못하겠어
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> response = new HashMap<>();
        List<EventCard> involvedEvents = eventService.findInvolvedEventsByMemberId(memberId);
        response.put("totalCnt", involvedEvents.size());
        response.put("involvedEvents", involvedEvents);

        return ResponseEntity.ok(response);
    }
}
