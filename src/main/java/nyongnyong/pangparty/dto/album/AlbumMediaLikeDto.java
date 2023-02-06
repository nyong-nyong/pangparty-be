package nyongnyong.pangparty.dto.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.album.AlbumMediaLike;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AlbumMediaLikeDto {


    private Long uid;
    private Long memberUid;
    private Long albumMediaUid;
    private LocalDateTime likeTime;

    // Entity -> Dto
    public AlbumMediaLikeDto(AlbumMediaLike albumMediaLike) {
        this.uid = albumMediaLike.getUid();
        this.memberUid = albumMediaLike.getMember().getUid();
        this.albumMediaUid = albumMediaLike.getAlbumMedia().getUid();
        this.likeTime = albumMediaLike.getLikeTime();
    }

}
