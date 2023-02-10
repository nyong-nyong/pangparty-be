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
public class AlbumMediaLike implements Serializable {

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

    private LocalDateTime likeTime;

    public AlbumMediaLike(LocalDateTime likeTime) {
        this.likeTime = likeTime;
    }

    @Builder
    public AlbumMediaLike(Member member, AlbumMedia albumMedia, LocalDateTime likeTime) {
        this.member = member;
        this.albumMedia = albumMedia;
        this.likeTime = likeTime;
    }

    /**
     * 연관관계 편의 메소드
     * @param member
     */
    public void changeMember(Member member) {
        this.member = member;
    }

    /**
     * 연관관계 편의 메소드
     * @param albumMedia
     */
    public void changeAlbumMedia(AlbumMedia albumMedia) {
        this.albumMedia = albumMedia;
        if (!albumMedia.getAlbumMediaLikes().contains(this)) {
            albumMedia.getAlbumMediaLikes().add(this);
        }
    }

}
