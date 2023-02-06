package nyongnyong.pangparty.entity.album;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "content", "createTime", "modifyTime"})
public class AlbumMediaComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_media_uid")
    private AlbumMedia albumMedia;

    @Lob
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    @Builder
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
