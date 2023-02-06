package nyongnyong.pangparty.dto.album;

import lombok.*;
import nyongnyong.pangparty.entity.album.Album;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AlbumDto {

    @Id
    private Long uid;
    private Long eventUid;
    @Builder.Default
    private List<Long> albumMediaUid = new ArrayList<>();

    @Builder
    public AlbumDto(Long uid, Long eventUid, List<Long> albumMediaUid) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.albumMediaUid = albumMediaUid;
    }

    // Entity -> Dto
    public AlbumDto(Album album) {
        this.uid = album.getUid();
        this.eventUid = album.getEvent().getUid();
        this.albumMediaUid = album.getAlbumMedia().stream().map(albumMedia -> albumMedia.getUid()).collect(Collectors.toList());
    }

}
