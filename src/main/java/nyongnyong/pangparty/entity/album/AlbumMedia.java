package nyongnyong.pangparty.entity.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "mediaUrl", "extension", "uploadTime", "takenTime", "takenLat", "takenLng"})
public class AlbumMedia {

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
