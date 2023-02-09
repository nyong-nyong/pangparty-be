package nyongnyong.pangparty.controller.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaCommentSimpleRes;
import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.service.album.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/events/{eventUid}/album")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMediaService albumMediaService;
    private final AlbumMediaCommentService albumMediaCommentService;
    private final AlbumMediaLikeService albumMediaLikeService;
    private final MediaService mediaService;
    private final MemberAuthService memberAuthService;

    public AlbumController(AlbumService albumService, AlbumMediaService albumMediaService, AlbumMediaCommentService albumMediaCommentService, AlbumMediaLikeService albumMediaLikeService, MediaService mediaService, MemberAuthService memberAuthService) {
        this.albumService = albumService;
        this.albumMediaService = albumMediaService;
        this.albumMediaCommentService = albumMediaCommentService;
        this.albumMediaLikeService = albumMediaLikeService;
        this.mediaService = mediaService;
        this.memberAuthService = memberAuthService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAlbumMediaList(@PathVariable Long eventUid, Pageable pageable) {
        if (eventUid < 0 || pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
            return ResponseEntity.badRequest().build();
        }
        Long albumUid = albumService.getAlbumUid(eventUid);
        log.debug("albumUid: {}", albumUid);
        System.out.println("albumUid: "+albumUid);
        Page<AlbumMediaSimpleRes> albumMedia = albumMediaService.getAlbumMediaList(albumUid, pageable);

        HashMap<String, Object> response = new HashMap<>();
        response.put("size", pageable.getPageSize());
        response.put("page", pageable.getPageNumber());
        response.put("itemCnt", albumMedia.getTotalElements());
        response.put("totalPageCnt", albumMedia.getTotalPages());
        response.put("media", albumMedia.getContent());

        return ResponseEntity.ok(response);
    }

//    @PostMapping("/")
//    public ResponseEntity<AlbumMediaSimpleRes> createAlbumMedia(@PathVariable Long eventUid, @RequestPart MultipartFile file) {
////        // InputStream으로 file을 전달
////        final String uri = "http://localhost:8080/media/"+eventUid+"/upload";
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
////        RestTemplate restTemplate = new RestTemplate();
////        AlbumMediaDetailRes result = restTemplate.exchange(uri, AlbumMediaDetailRes.class);
//        Path imgPath = Path.of(file.getOriginalFilename());
//        String contentType;
//        try {
//            contentType = Files.probeContentType(imgPath);
//        } catch (IOException e) {
//            log.debug(e.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
//        if (contentType.startsWith("video")) {  // video
//        } else if (contentType.startsWith("image")) {   // image
//            //TODO: 이미지
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//        mediaService.saveMedia(file);
//        AlbumMediaSimpleRes albumMedia = albumMediaService.createAlbumMedia(result);
//
//        return new ResponseEntity<>(albumMedia, HttpStatus.CREATED);
//    }

    @GetMapping("/{mediaUid}")
    public ResponseEntity<?> getAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        if (eventUid < 0 || mediaUid < 0) {
            return ResponseEntity.badRequest().build();
        }
        // TODO: memberUid
//        Long memberUid = memberAuthService.getMemberUid();
        Long memberUid = 3L;
        AlbumMediaDetailRes albumMediaDetailRes = albumMediaService.getAlbumMedia(mediaUid);
        if (albumMediaDetailRes == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(albumMediaDetailRes);
    }

    @DeleteMapping("/{mediaUid}")
    public ResponseEntity<?> deleteAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        if (eventUid < 0 || mediaUid < 0) {
            return ResponseEntity.badRequest().build();
        }
        // TODO: memberUid
//        Long memberUid = memberAuthService.getMemberUid();
        Long memberUid = 3L;
        if (!albumMediaService.isAlbumMediaOwner(memberUid, mediaUid)) {
            return ResponseEntity.badRequest().build();
        }
        albumMediaService.deleteAlbumMedia(mediaUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{mediaUid}/comments")
    public ResponseEntity<?> getAlbumMediaComments(@PathVariable Long eventUid, @PathVariable Long mediaUid, Pageable pageable) {
        if (eventUid < 0 || pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
            return ResponseEntity.badRequest().build();
        }
        Page<AlbumMediaCommentSimpleRes> comments = albumMediaCommentService.getAlbumMediaCommentList(mediaUid, pageable);

        return new ResponseEntity<>();
    }

    @PostMapping("/{mediaUid}/comments")
    public ResponseEntity<AlbumMediaSimpleRes> createAlbumMediaComment(@PathVariable Long eventUid, @PathVariable Long mediaUid, @RequestBody String comment) {
        // TODO: memberUid
//        memberUid = memberAuthService.getMemberUid();
        Long memberUid = 3L;
        albumMediaCommentService.createAlbumMediaComment(memberUid, comment);
        return "album";
    }

//    @DeleteMapping("/{mediaUid}/comments/{commentUid}")
//    public ResponseEntity<?> deleteAlbumMediaComment(@PathVariable Long eventUid, @PathVariable Long mediaUid, @PathVariable Long commentUid) {
//        // TODO: memberUid
//        memberUid = memberAuthService.getMemberUid();
//        albumMediaCommentService.deleteAlbumMediaComment(memberUid, commentUid);
//        return "album";
//    }
//
//    @PostMapping("/{mediaUid}/like")
//    public ResponseEntity<?> likeAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
//        // TODO: memberUid
//        memberUid = memberAuthService.getMemberUid();
//        albumMediaLikeService.likeAlbumMedia(memberUid, mediaUid);
//        return "album";
//    }
//
//    @DeleteMapping("/{mediaUid}/like")
//    public ResponseEntity<?> unlikeAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
//        // TODO: memberUid
//        memberUid = memberAuthService.getMemberUid();
//        albumMediaLikeService.unlikeAlbumMedia(memberUid, mediaUid);
//        return "album";
//    }
}
