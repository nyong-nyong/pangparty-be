package nyongnyong.pangparty.dto.album;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AlbumMediaDetailRes {


    private Long uid;
    private Long eventUid;
    private Long memberUid;
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

    public AlbumMediaDetailRes(Long uid, Long eventUid, Long memberUid, String mediaUrl, String extension, LocalDateTime uploadTime, LocalDateTime takenTime, String takenLat, String takenLng, Long prevAlbumMediaUid, Long nextAlbumMediaUid, int likeCount, boolean isLiked, LocalDateTime likeTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberUid = memberUid;
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

}
