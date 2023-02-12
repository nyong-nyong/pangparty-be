package nyongnyong.pangparty.dto.album;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AlbumMediaDetailRes {


    private Long uid;
    private String memberId;
    @URL(message = "URL 형식이 아닙니다.")
    private String mediaUrl;
    private String extension;
    private LocalDateTime uploadTime;
    private LocalDateTime takenTime;
    private String takenLat;
    private String takenLng;
    private Long prevAlbumMediaUid;
    private Long nextAlbumMediaUid;
    private int likeCount;
    private boolean isLiked;
    private LocalDateTime likeTime;

    @Builder
    public AlbumMediaDetailRes(Long uid, String memberId, String mediaUrl, String extension, LocalDateTime uploadTime, LocalDateTime takenTime, String takenLat, String takenLng, Long prevAlbumMediaUid, Long nextAlbumMediaUid, int likeCount, boolean isLiked, LocalDateTime likeTime) {
        this.uid = uid;
        this.memberId = memberId;
        this.mediaUrl = mediaUrl;
        this.extension = extension;
        this.uploadTime = uploadTime;
        this.takenTime = takenTime;
        this.takenLat = takenLat;
        this.takenLng = takenLng;
        this.prevAlbumMediaUid = prevAlbumMediaUid;
        this.nextAlbumMediaUid = nextAlbumMediaUid;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
        this.likeTime = likeTime;
    }

    @Builder
    public AlbumMediaDetailRes(AlbumMedia albumMedia, Long prevAlbumMediaUid, Long nextAlbumMediaUid) {
        this.uid = albumMedia.getUid();
        this.memberId = albumMedia.getMember().getMemberProfile().getId();
        this.mediaUrl = albumMedia.getMediaUrl();
        this.extension = albumMedia.getExtension();
        this.uploadTime = albumMedia.getUploadTime();
        this.takenTime = albumMedia.getTakenTime();
        this.takenLat = albumMedia.getTakenLat();
        this.takenLng = albumMedia.getTakenLng();
        this.prevAlbumMediaUid = prevAlbumMediaUid;
        this.nextAlbumMediaUid = nextAlbumMediaUid;
        this.likeCount = albumMedia.getAlbumMediaLikes().size();
        this.isLiked = albumMedia.getAlbumMediaLikes().stream().anyMatch(albumMediaLike -> albumMediaLike.getMember().getUid().equals(albumMedia.getMember().getUid()));
        this.likeTime = albumMedia.getAlbumMediaLikes().stream().filter(albumMediaLike -> albumMediaLike.getMember().getUid().equals(albumMedia.getMember().getUid())).findFirst().map(albumMediaLike -> albumMediaLike.getLikeTime()).orElse(null);
    }
}
