package nyongnyong.pangparty.entity.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"likeTime"})
public class AlbumMediaLike {

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

    public void changeAlbumMedia(AlbumMedia albumMedia) {
        this.albumMedia = albumMedia;
        if (!albumMedia.getAlbumMediaLikes().contains(this)) {
            albumMedia.getAlbumMediaLikes().add(this);
        }
    }

    public void changeMember(Member member) {
        this.member = member;
    }

}
