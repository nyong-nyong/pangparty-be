package nyongnyong.pangparty.dto.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AlbumMediaDto {

    @Id
    private Long uid;
    @NotNull(message = "앨범 UID는 필수입니다.")
    private Long albumUid;
    @UniqueElements(message = "중복된 댓글 UID가 있습니다.")
    private List<Long> albumMediaCommentsUid;
    @UniqueElements(message = "중복된 좋아요 UID가 있습니다.")
    private List<Long> albumMediaLikesUid;
    @URL(message = "URL 형식이 아닙니다.")
    private String mediaUrl;
    private String extension;
    private LocalDateTime uploadTime;
    private LocalDateTime takenTime;
    private String takenLat;
    private String takenLng;

    // Entity -> Dto
    public AlbumMediaDto(AlbumMedia albumMedia) {
        this.uid = albumMedia.getUid();
        this.albumUid = albumMedia.getAlbum().getUid();
        this.albumMediaCommentsUid = albumMedia.getAlbumMediaComments().stream().map(albumMediaComment -> albumMediaComment.getUid()).collect(Collectors.toList());
        this.albumMediaLikesUid = albumMedia.getAlbumMediaLikes().stream().map(albumMediaLike -> albumMediaLike.getUid()).collect(Collectors.toList());
        this.mediaUrl = albumMedia.getMediaUrl();
        this.extension = albumMedia.getExtension();
        this.uploadTime = albumMedia.getUploadTime();
        this.takenTime = albumMedia.getTakenTime();
        this.takenLat = albumMedia.getTakenLat();
        this.takenLng = albumMedia.getTakenLng();
    }

}
