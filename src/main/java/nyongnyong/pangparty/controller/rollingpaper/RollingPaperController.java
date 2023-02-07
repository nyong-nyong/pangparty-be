package nyongnyong.pangparty.controller.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerRequestDto;
import nyongnyong.pangparty.service.rollingpaper.RollingPaperService;
import nyongnyong.pangparty.service.rollingpaper.RollingPaperStickerService;
import nyongnyong.pangparty.service.rollingpaper.StickerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/events/{eventUid}/rollingpaper")
@RequiredArgsConstructor
public class RollingPaperController {

    private final StickerService stickerService;
    private final RollingPaperService rollingPaperService;
    private final RollingPaperStickerService rollingPaperStickerService;

    @GetMapping("/{rollingPaperUid}/stickers")
    public ResponseEntity<?> findRollingPaperStickersByTopLoc(@PathVariable("eventUid") Long eventUid, @PathVariable("rollingPaperUid") Long rollingPaperUid, @RequestParam int topStart, @RequestParam int topEnd) {
        // Validate Path Variable
        if (eventUid < 0 || rollingPaperUid < 0 || topStart < 0 || topEnd < 0 || topStart > topEnd) {
            return ResponseEntity.badRequest().build();
        }

        // Validate eventUid and rollingPaperUid
        // TODO validate eventUid
        if (!rollingPaperService.isExistRollingPaperByRollingPaperUid(rollingPaperUid)) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("rollingPaperStickers", rollingPaperStickerService.findRollingPaperStickersByTopLoc(rollingPaperUid, topStart, topEnd));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{rollingPaperUid}/stickers")
    public ResponseEntity<?> addRollingPaperSticker(@PathVariable("eventUid") Long eventUid, @PathVariable("rollingPaperUid") Long rollingPaperUid, @RequestBody RollingPaperStickerRequestDto rollingPaperStickerRequestDto) {
        // TODO check login status

        // Validate Path Variable and Request Body
        if (eventUid < 0 || rollingPaperUid < 0 || rollingPaperStickerRequestDto.getLeftLoc() < 0 || rollingPaperStickerRequestDto.getTopLoc() < 0) {
            return ResponseEntity.badRequest().build();
        }

        // Validate eventUid and rollingPaperUid, stickerUid
        // TODO validate eventUid
        if (!rollingPaperService.isExistRollingPaperByRollingPaperUid(rollingPaperUid) || !stickerService.isExistStickerByStickerUid(rollingPaperStickerRequestDto.getStickerUid())) {
            return ResponseEntity.badRequest().build();
        }

        rollingPaperStickerRequestDto.setRollingPaperUid(rollingPaperUid);
        // TODO set member
        Long uid = rollingPaperStickerService.addRollingPaperSticker(rollingPaperStickerRequestDto);

        // TODO set proper uri
        URI uri = URI.create("/stickers/" + uid);

        return ResponseEntity.created(uri).build();
    }
}
