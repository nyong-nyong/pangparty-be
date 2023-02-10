package nyongnyong.pangparty.controller.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaCommentSimpleRes;
import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.InvalidTokenException;
import nyongnyong.pangparty.service.album.AlbumMediaCommentService;
import nyongnyong.pangparty.service.album.AlbumMediaService;
import nyongnyong.pangparty.service.album.AlbumService;
import nyongnyong.pangparty.service.album.MediaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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

    /**
     * 앨범 미디어 전체 조회
     * @param eventUid
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAlbumMediaList(@PathVariable Long eventUid, Pageable pageable) {
        if (eventUid < 0 || pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
            return ResponseEntity.badRequest().build();
        }
        Long albumUid = albumService.getAlbum(eventUid).getUid();
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

    /**
     * 앨범 미디어 추가
     * @param eventUid
     * @param file
     * @return
     */
//    @PostMapping("/")
//    public ResponseEntity<AlbumMediaSimpleRes> createAlbumMedia(@RequestHeader(required = false, value = "Authorization") String token, @PathVariable Long eventUid, @RequestPart MultipartFile file) {
////        // InputStream으로 file을 전달
////        final String uri = "http://localhost:8080/media/"+eventUid+"/upload";
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
////        RestTemplate restTemplate = new RestTemplate();
////        AlbumMediaDetailRes result = restTemplate.exchange(uri, AlbumMediaDetailRes.class);
//        //TODO: memberUid
//        try {
//            Long memberUid = memberAuthService.getMemberUid(token);
//        }catch (MemberNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }catch (TokenInvalidException e){
//            return ResponseEntity.badRequest().build();
//        }
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

    /**
     * 앨범 미디어 상세 조회
     * @param eventUid
     * @param mediaUid
     * @return
     */
    @GetMapping("/{mediaUid}")
    public ResponseEntity<?> getAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        if (eventUid < 0 || mediaUid < 0) {
            return ResponseEntity.badRequest().build();
        }
        try {
            AlbumMediaDetailRes albumMediaDetailRes = albumMediaService.getAlbumMedia(mediaUid);
            return ResponseEntity.ok(albumMediaDetailRes);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 앨범 미디어 삭제
     * @param eventUid
     * @param mediaUid
     * @return
     */
    @DeleteMapping("/{mediaUid}")
    public ResponseEntity<?> deleteAlbumMedia(@RequestHeader(required = false, value = "Authorization") String token, @PathVariable Long eventUid, @PathVariable Long mediaUid) {
        if (eventUid < 0 || mediaUid < 0) {
            return ResponseEntity.badRequest().build();
        }
        // TODO: memberUid
        try {
            Long memberUid = memberAuthService.getMemberUid(token);
            if (!albumMediaService.isAlbumMediaOwner(memberUid, mediaUid)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            albumMediaService.deleteAlbumMedia(mediaUid);
            return ResponseEntity.noContent().build();
        }catch (MemberNotFoundException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (TokenInvalidException e){
            return ResponseEntity.badRequest().build();
        }catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 앨범 미디어 댓글 전체 조회
     * @param eventUid
     * @param mediaUid
     * @return
     */
    @GetMapping("/{mediaUid}/comments")
    public ResponseEntity<?> getAlbumMediaComments(@PathVariable Long eventUid, @PathVariable Long mediaUid, Pageable pageable) {
        if (eventUid < 0 || pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Page<AlbumMediaCommentSimpleRes> comments = albumMediaCommentService.getAlbumMediaCommentList(mediaUid, pageable);

            HashMap<String, Object> response = new HashMap<>();
            response.put("size", pageable.getPageSize());
            response.put("page", pageable.getPageNumber());
            response.put("itemCnt", comments.getTotalElements());
            response.put("totalPageCnt", comments.getTotalPages());
            response.put("media", comments.getContent());

            return ResponseEntity.ok(response);
        }catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 앨범 미디어 댓글 추가
     * @param eventUid
     * @param mediaUid
     * @param comment
     * @return
     */
    @PostMapping("/{mediaUid}/comments")
    public ResponseEntity<AlbumMediaCommentSimpleRes> createAlbumMediaComment(@RequestHeader(required = false, value = "Authorization") String token, @PathVariable Long eventUid, @PathVariable Long mediaUid, @RequestBody String comment) {
        if (eventUid < 0 || mediaUid < 0 || comment == null || comment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Long memberUid = memberAuthService.getMemberUid(token);
        }catch (MemberNotFoundException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (TokenInvalidException e){
            return ResponseEntity.badRequest().build();
        }
        AlbumMediaCommentSimpleRes albumMediaCommentSimpleRes = albumMediaCommentService.createAlbumMediaComment(memberUid, mediaUid, comment);
        return ResponseEntity.ok(albumMediaCommentSimpleRes);
    }

    /**
     * 앨범 미디어 댓글 삭제
     * @param eventUid
     * @param mediaUid
     * @param commentUid
     * @return
     */
    @DeleteMapping("/{mediaUid}/comments/{commentUid}")
    public ResponseEntity<?> deleteAlbumMediaComment(@RequestHeader(required = false, value = "Authorization") String token, @PathVariable Long eventUid, @PathVariable Long mediaUid, @PathVariable Long commentUid) {
        // TODO: memberUid
        try {
            Long memberUid = memberAuthService.getMemberUid(token);
            if (!albumMediaCommentService.isAlbumMediaCommentOwner(memberUid, commentUid)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            albumMediaCommentService.deleteAlbumMediaComment(memberUid, commentUid);
            return ResponseEntity.noContent().build();
        }catch (MemberNotFoundException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (TokenInvalidException e){
            return ResponseEntity.badRequest().build();
        }catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 앨범 미디어 좋아요
     * @param eventUid
     * @param mediaUid
     * @return
     */
//    @PostMapping("/{mediaUid}/like")
//    public ResponseEntity<?> likeAlbumMedia(@RequestHeader(required = false, value = "Authorization") String token, @PathVariable Long eventUid, @PathVariable Long mediaUid) {
//        // TODO: memberUid
//        try {
//            Long memberUid = memberAuthService.getMemberUid(token);
//            albumMediaLikeService.likeAlbumMedia(memberUid, mediaUid);
//            return "album";
//        }catch (MemberNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }catch (TokenInvalidException e){
//            return ResponseEntity.badRequest().build();
//        }
//    }

    /**
     * 앨범 미디어 좋아요 취소
     * @param eventUid
     * @param mediaUid
     * @return
     */
//    @DeleteMapping("/{mediaUid}/like")
//    public ResponseEntity<?> unlikeAlbumMedia(@RequestHeader(required = false, value = "Authorization") String token, @PathVariable Long eventUid, @PathVariable Long mediaUid) {
//        // TODO: memberUid
//        try {
//            Long memberUid = memberAuthService.getMemberUid(token);
//            albumMediaLikeService.unlikeAlbumMedia(memberUid, mediaUid);
//            return ResponseEntity.noContent().build();
//        }catch (MemberNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }catch (TokenInvalidException e){
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
