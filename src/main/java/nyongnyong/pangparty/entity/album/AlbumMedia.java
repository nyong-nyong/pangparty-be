package nyongnyong.pangparty.entity.album;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "mediaUrl", "extension", "uploadTime", "takenTime", "takenLat", "takenLng"})
public class AlbumMedia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_uid")
    private Album album;

    @OneToMany(mappedBy = "albumMedia")
    private List<AlbumMediaComment> albumMediaComments;

    @OneToMany(mappedBy = "albumMedia")
    private List<AlbumMediaLike> albumMediaLikes;

    private String mediaUrl;
    private String extension;

    private LocalDateTime uploadTime;
    private LocalDateTime takenTime;
    private String takenLat;
    private String takenLng;

    @Builder
    public AlbumMedia(String mediaUrl, String extension, LocalDateTime uploadTime, LocalDateTime takenTime, String takenLat, String takenLng) {
        this.mediaUrl = mediaUrl;
        this.extension = extension;
        this.uploadTime = uploadTime;
        this.takenTime = takenTime;
        this.takenLat = takenLat;
        this.takenLng = takenLng;
    }

    public void addAlbumMediaComment(AlbumMediaComment albumMediaComment) {
        this.albumMediaComments.add(albumMediaComment);
            if (albumMediaComment.getAlbumMedia() != this) {
                albumMediaComment.changeAlbumMedia(this);
            }
        }

        public void addAlbumMediaLike(AlbumMediaLike albumMediaLike) {
            this.albumMediaLikes.add(albumMediaLike);
            if (albumMediaLike.getAlbumMedia() != this) {
                albumMediaLike.changeAlbumMedia(this);
        }
    }
    public void changeAlbum(Album album) {
        this.album = album;
        if (!album.getAlbumMedia().contains(this)) {
            album.getAlbumMedia().add(this);
        }
    }
}
