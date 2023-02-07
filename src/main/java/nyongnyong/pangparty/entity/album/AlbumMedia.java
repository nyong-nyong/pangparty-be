package nyongnyong.pangparty.entity.album;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AlbumMedia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_uid")
    @ToString.Exclude
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    @OneToMany(mappedBy = "albumMedia")
    @ToString.Exclude
    private List<AlbumMediaComment> albumMediaComments;

    @OneToMany(mappedBy = "albumMedia")
    @ToString.Exclude
    private List<AlbumMediaLike> albumMediaLikes;

    private String mediaUrl;
    private String extension;
    private LocalDateTime uploadTime;
    private LocalDateTime takenTime;
    private String takenLat;
    private String takenLng;

    public void setAlbumMediaLikes(List<AlbumMediaLike> albumMediaLikes) {
        this.albumMediaLikes = albumMediaLikes;
    }

    public void setAlbumMediaComments(List<AlbumMediaComment> albumMediaComments) {
        this.albumMediaComments = albumMediaComments;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Builder
    public AlbumMedia(String mediaUrl, String extension, LocalDateTime uploadTime, LocalDateTime takenTime, String takenLat, String takenLng) {
        this.mediaUrl = mediaUrl;
        this.extension = extension;
        this.uploadTime = uploadTime;
        this.takenTime = takenTime;
        this.takenLat = takenLat;
        this.takenLng = takenLng;
    }

    /**
     * 연관관계 편의 메서드
     * @param albumMediaComment
     */
    public void addAlbumMediaComment(AlbumMediaComment albumMediaComment) {
        this.albumMediaComments.add(albumMediaComment);
        if (albumMediaComment.getAlbumMedia() != this) {
            albumMediaComment.changeAlbumMedia(this);
        }
    }

    /**
     * 연관관계 편의 메서드
     * @param albumMediaLike
     */
    public void addAlbumMediaLike(AlbumMediaLike albumMediaLike) {
        this.albumMediaLikes.add(albumMediaLike);
        if (albumMediaLike.getAlbumMedia() != this) {
            albumMediaLike.changeAlbumMedia(this);
        }
    }

    /**
     * 연관관계 편의 메서드
     * @param album
     */
    public void changeAlbum(Album album) {
        this.album = album;
        if (!album.getAlbumMedia().contains(this)) {
            album.getAlbumMedia().add(this);
        }
    }
}
