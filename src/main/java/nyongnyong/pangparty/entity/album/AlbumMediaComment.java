package nyongnyong.pangparty.entity.album;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AlbumMediaComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_media_uid")
    @ToString.Exclude
    private AlbumMedia albumMedia;

    @Lob
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    /**
     * 생성자
     * @param content
     * @param createTime
     * @param modifyTime
     */
    @Builder
    public AlbumMediaComment(String content, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * 연관관계 편의 메소드
     * @param albumMedia
     */
    public void changeAlbumMedia(AlbumMedia albumMedia) {
        this.albumMedia = albumMedia;
        if (!albumMedia.getAlbumMediaComments().contains(this)) {
            albumMedia.getAlbumMediaComments().add(this);
        }
    }

}
