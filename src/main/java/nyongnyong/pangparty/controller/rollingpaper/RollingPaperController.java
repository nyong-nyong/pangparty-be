package nyongnyong.pangparty.controller.rollingpaper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperPieceReq;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperPieceRes;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerReq;
import nyongnyong.pangparty.exception.EventNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.RollingPaperNotFoundException;
import nyongnyong.pangparty.service.auth.MemberAuthService;
import nyongnyong.pangparty.service.rollingpaper.RollingPaperPieceService;
import nyongnyong.pangparty.service.rollingpaper.RollingPaperService;
import nyongnyong.pangparty.service.rollingpaper.RollingPaperStickerService;
import nyongnyong.pangparty.service.rollingpaper.StickerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/events/{eventUid}/rollingpaper")
@RequiredArgsConstructor
public class RollingPaperController {

    private final MemberAuthService memberAuthService;
    private final StickerService stickerService;
    private final RollingPaperService rollingPaperService;
    private final RollingPaperPieceService rollingPaperPieceService;
    private final RollingPaperStickerService rollingPaperStickerService;

    @GetMapping("/{rollingPaperUid}/pieces")
    public ResponseEntity<?> findRollingPaperPieces(@PathVariable("eventUid") Long eventUid,
                                                    @PathVariable("rollingPaperUid") Long rollingPaperUid, Pageable pageable) {
        // validate path variable
        if (eventUid < 0 || rollingPaperUid < 0 || pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Page<RollingPaperPieceRes> rollingPaperPiecePage = rollingPaperPieceService.findRollingPaperPieces(eventUid, rollingPaperUid, pageable);

            Map<String, Object> response = Map.of("size", pageable.getPageSize(),
                    "page", pageable.getPageNumber(),
                    "itemCnt", rollingPaperPiecePage.getTotalElements(),
                    "totalPageCnt", rollingPaperPiecePage.getTotalPages(),
                    "rollingPaperPieces", rollingPaperPiecePage.getContent());

            return ResponseEntity.ok(response);
        } catch (EventNotFoundException | RollingPaperNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{rollingPaperUid}/pieces")
    public ResponseEntity<?> addRollingPaperPiece(@RequestHeader(required = false, value = "Authorization") String token,
                                                  @PathVariable("eventUid") Long eventUid,
                                                  @PathVariable("rollingPaperUid") Long rollingPaperUid,
                                                  @RequestBody RollingPaperPieceReq rollingPaperPieceReq) {
        // validate path variable
        if (eventUid < 0 || rollingPaperUid < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Long uid;
            if (token == null) {
                rollingPaperPieceReq.setRollingPaperUid(rollingPaperUid);
                uid = rollingPaperPieceService.addRollingPaperPiece(eventUid, rollingPaperPieceReq);
            } else {
                // check login status (token)
                Long memberUid = memberAuthService.getMemberUid(token);

                // set rolling paper
                rollingPaperPieceReq.setRollingPaperUid(rollingPaperUid);
                uid = rollingPaperPieceService.addRollingPaperPiece(memberUid, eventUid, rollingPaperPieceReq);
            }

            return ResponseEntity.created(URI.create("/events/" + eventUid + "/rollingpaper/" + rollingPaperUid + "/pieces/" + uid)).build();
        } catch (MemberNotFoundException | EventNotFoundException | RollingPaperNotFoundException |
                 IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{rollingPaperUid}/stickers")
    public ResponseEntity<?> findRollingPaperStickersByTopLoc(@PathVariable("eventUid") Long eventUid,
                                                              @PathVariable("rollingPaperUid") Long rollingPaperUid,
                                                              @RequestParam int topStart, @RequestParam int topEnd) {
        // validate path variable
        if (eventUid < 0 || rollingPaperUid < 0 || topStart < 0 || topEnd < 0 || topStart > topEnd) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Map<String, Object> response = Map.of("rollingPaperStickers", rollingPaperStickerService.findRollingPaperStickersByTopLoc(eventUid, rollingPaperUid, topStart, topEnd));

            return ResponseEntity.ok(response);
        } catch (EventNotFoundException | RollingPaperNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{rollingPaperUid}/stickers")
    public ResponseEntity<?> addRollingPaperSticker
            (@RequestHeader(required = false, value = "Authorization") String token,
             @PathVariable("eventUid") Long eventUid,
             @PathVariable("rollingPaperUid") Long rollingPaperUid,
             @RequestBody RollingPaperStickerReq rollingPaperStickerReq) {
        // validate path variable & request body
        if (eventUid < 0 || rollingPaperUid < 0 || rollingPaperStickerReq.getLeftLoc() < 0 || rollingPaperStickerReq.getTopLoc() < 0) {
            return ResponseEntity.badRequest().build();
        }


        try {
            // check login status (token)
            Long memberUid = memberAuthService.getMemberUid(token);

        } catch (MemberNotFoundException | EventNotFoundException | RollingPaperNotFoundException |
                 IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        // Validate eventUid and rollingPaperUid, stickerUid
        // TODO validate eventUid
        if (!rollingPaperService.isExistRollingPaperByRollingPaperUid(rollingPaperUid) || !stickerService.isExistStickerByStickerUid(rollingPaperStickerReq.getStickerUid())) {
            return ResponseEntity.badRequest().build();
        }

        rollingPaperStickerReq.setRollingPaperUid(rollingPaperUid);
        // TODO set member
        Long uid = rollingPaperStickerService.addRollingPaperSticker(rollingPaperStickerReq);

        return ResponseEntity.created(URI.create("/events/" + eventUid + "/rollingpaper/" + rollingPaperUid + "/stickers/" + uid)).build();
    }
}
