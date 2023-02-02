package nyongnyong.pangparty.entity.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "content", "createTime", "modifyTime"})
public class AlbumMediaComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_media_uid")
    private AlbumMedia albumMedia;

    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    public AlbumMediaComment(String content, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public void changeAlbumMedia(AlbumMedia albumMedia) {
        this.albumMedia = albumMedia;
        if (!albumMedia.getAlbumMediaComments().contains(this)) {
            albumMedia.getAlbumMediaComments().add(this);
        }
    }

}
