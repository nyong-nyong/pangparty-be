package nyongnyong.pangparty.controller.auth;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.service.auth.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    @GetMapping("/members/{memberId}/id/{notificationId}")
    public ResponseEntity<?> readNotification(@PathVariable("memberId") String memberId,
                                              @PathVariable("notificationId") String notificationId) {
        notificationService.readNotification(memberId, notificationId);
        return ResponseEntity.ok().build();
    }

}
