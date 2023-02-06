package nyongnyong.pangparty.entity.album;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "likeTime"})
public class AlbumMediaLike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_media_uid")
    private AlbumMedia albumMedia;

    private LocalDateTime likeTime;

    @Builder
    public AlbumMediaLike(LocalDateTime likeTime) {
        this.likeTime = likeTime;
    }

    public void changeMember(Member member) {
        this.member = member;
    }

    public void changeAlbumMedia(AlbumMedia albumMedia) {
        this.albumMedia = albumMedia;
        if (!albumMedia.getAlbumMediaLikes().contains(this)) {
            albumMedia.getAlbumMediaLikes().add(this);
        }
    }

}
