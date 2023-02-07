package nyongnyong.pangparty.controller.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaCommentSimpleRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.service.album.AlbumMediaCommentService;
import nyongnyong.pangparty.service.album.AlbumMediaService;
import nyongnyong.pangparty.service.album.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;

@Slf4j
@RequestMapping("/events/{eventUid}/album")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMediaService albumMediaService;
    private final AlbumMediaCommentService albumMediaCommentService;

    public AlbumController(AlbumService albumService, AlbumMediaService albumMediaService, AlbumMediaCommentService albumMediaCommentService) {
        this.albumService = albumService;
        this.albumMediaService = albumMediaService;
        this.albumMediaCommentService = albumMediaCommentService;
    }

    @GetMapping("/")
    public String getAlbumMediaList(@PathVariable Long eventUid, @RequestParam int page, @RequestParam int size) {
        Long albumUid = albumService.getAlbumUid(eventUid);
        Page<AlbumMediaSimpleRes> albumMedia= albumMediaService.getAlbumMediaList(albumUid, PageRequest.of(page, size));

        return "album";
    }

    @PostMapping("/")
    public String createAlbumMedia(@PathVariable Long eventUid, @RequestPart MultipartFile file) {
        return "album";
    }

    @GetMapping("/{mediaUid}")
    public String getAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        return "album";
    }

    @DeleteMapping("/{mediaUid}")
    public String deleteAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        return "album";
    }

    @GetMapping("/{mediaUid}/comments")
    public String getAlbumMediaComments(@PathVariable Long eventUid, @PathVariable Long mediaUid, @RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<AlbumMediaCommentSimpleRes> comments = albumMediaCommentService.getAlbumMediaCommentList(mediaUid, pageRequest);
        return "album";
    }

    @PostMapping("/{mediaUid}/comments")
    public String createAlbumMediaComment(@PathVariable Long eventUid, @PathVariable Long mediaUid, @RequestBody String comment) {
        return "album";
    }

    @DeleteMapping("/{mediaUid}/comments/{commentUid}")
    public String deleteAlbumMediaComment(@PathVariable Long eventUid, @PathVariable Long mediaUid, @PathVariable Long commentUid) {
        return "album";
    }

    @PostMapping("/{mediaUid}/like")
    public String likeAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        return "album";
    }

    @DeleteMapping("/{mediaUid}/like")
    public String unlikeAlbumMedia(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        return "album";
    }
}
