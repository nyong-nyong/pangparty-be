package nyongnyong.pangparty.controller.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.service.rollingpaper.StickerService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stickers")
@RequiredArgsConstructor
public class StickerController {

    private final StickerService stickerService;

    @GetMapping
    public ResponseEntity<?> findStickerList(Pageable pageable) {
        return ResponseEntity.ok(stickerService.findStickerList(pageable).getContent());
    }

}
