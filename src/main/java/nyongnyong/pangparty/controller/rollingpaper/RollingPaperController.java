package nyongnyong.pangparty.controller.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerDto;
import nyongnyong.pangparty.service.rollingpaper.RollingPaperService;
import nyongnyong.pangparty.service.rollingpaper.RollingPaperStickerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/events/{eventUid}/rollingpaper")
@RequiredArgsConstructor
public class RollingPaperController {

    private final RollingPaperService rollingPaperService;
    private final RollingPaperStickerService rollingPaperStickerService;

    @GetMapping
    public ResponseEntity<?> findRollingPaper(@PathVariable("eventUid") Long eventUid) {
        // TODO check if eventUid is valid

        return ResponseEntity.ok(rollingPaperService.findRollingPaperByEventUid(eventUid));
    }


    @GetMapping("/{rollingPaperUid}/stickers")
    public ResponseEntity<?> findRollingPaperStickersByTopLoc(@PathVariable("eventUid") Long eventUid, @PathVariable("rollingPaperUid") Long rollingPaperUid, @RequestParam int topStart, @RequestParam int topEnd) {
        if (topStart < 0 || topEnd < 0 || topStart > topEnd) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("rollingPaperStickers", rollingPaperStickerService.findRollingPaperStickersByTopLoc(eventUid, rollingPaperUid, topStart, topEnd));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{rollingPaperUid}/stickers")
    public ResponseEntity<?> addRollingPaperSticker(@RequestBody RollingPaperStickerDto rollingPaperStickerDto) {
        // TODO check login status
        // TODO check if eventUid/rollingPaperUid/stickerUid/topLoc/leftLoc is valid
        // TODO check if stickerUid is added to RollingPaperSticker table

        Long uid = rollingPaperStickerService.addRollingPaperSticker(rollingPaperStickerDto.toEntity());
        URI uri = URI.create("/stickers/" + uid);

        return ResponseEntity.created(uri).build();
    }
}
