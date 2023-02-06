package nyongnyong.pangparty.controller.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumDto;
import nyongnyong.pangparty.dto.album.AlbumMediaCommentDto;
import nyongnyong.pangparty.dto.album.AlbumMediaLikeDto;
import nyongnyong.pangparty.service.album.AlbumMediaCommentService;
import nyongnyong.pangparty.service.album.AlbumMediaLikeService;
import nyongnyong.pangparty.service.album.AlbumMediaService;
import nyongnyong.pangparty.service.album.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/events/{eventUid}/album")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMediaService albumMediaService;
    private final AlbumMediaCommentService albumMediaCommentService;
    private final AlbumMediaLikeService albumMediaLikeService;

    public AlbumController(AlbumService albumService, AlbumMediaService albumMediaService, AlbumMediaCommentService albumMediaCommentService, AlbumMediaLikeService albumMediaLikeService) {
        this.albumService = albumService;
        this.albumMediaService = albumMediaService;
        this.albumMediaCommentService = albumMediaCommentService;
        this.albumMediaLikeService = albumMediaLikeService;
    }

    @GetMapping("/")
    public String getAlbumList(@PathVariable Long eventUid, @RequestParam int page, @RequestParam int size) {

        return "album";
    }

    @PostMapping("/")
    public String createAlbum(@PathVariable Long eventUid, @RequestBody AlbumDto albumDto) {
        return "album";
    }

    @GetMapping("/{mediaUid}")
    public String getAlbum(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        return "album";
    }

    @DeleteMapping("/{mediaUid}")
    public String deleteAlbum(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        return "album";
    }

    @GetMapping("/{mediaUid}/comments")
    public String getAlbumComments(@PathVariable Long eventUid, @PathVariable Long mediaUid, @RequestParam int page, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<AlbumMediaCommentDto> comments = albumMediaCommentService.getAlbumMediaCommentList(mediaUid, pageRequest);
        return "album";
    }

    @PostMapping("/{mediaUid}/comments")
    public String createAlbumComment(@PathVariable Long eventUid, @PathVariable Long mediaUid, @RequestBody AlbumMediaCommentDto comment) {
        return "album";
    }

    @DeleteMapping("/{mediaUid}/comments/{commentUid}")
    public String deleteAlbumComment(@PathVariable Long eventUid, @PathVariable Long mediaUid, @PathVariable Long commentUid) {
        return "album";
    }

    @PostMapping("/{mediaUid}/like")
    public String likeAlbum(@PathVariable Long eventUid, @PathVariable Long mediaUid, @RequestBody AlbumMediaLikeDto like) {
        return "album";
    }

    @DeleteMapping("/{mediaUid}/like")
    public String unlikeAlbum(@PathVariable Long eventUid, @PathVariable Long mediaUid) {
        return "album";
    }
}
