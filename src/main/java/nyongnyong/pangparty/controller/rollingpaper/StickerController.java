package nyongnyong.pangparty.controller.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.entity.rollingpaper.Sticker;
import nyongnyong.pangparty.service.rollingpaper.StickerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stickers")
@RequiredArgsConstructor
public class StickerController {

    private final StickerService stickerService;

    @GetMapping
    public ResponseEntity<?> findStickerList(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();

        Page<Sticker> stickerPage = stickerService.findStickerList(pageable);
        response.put("size", pageable.getPageSize());
        response.put("page", pageable.getPageNumber());
        response.put("itemCnt", stickerPage.getTotalElements());
        response.put("totalPageCnt", stickerPage.getTotalPages());
        response.put("stickers", stickerPage.getContent());

        return ResponseEntity.ok(response);
    }

}
