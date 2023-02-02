package nyongnyong.pangparty.entity.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.event.Event;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid"})
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne
    @JoinColumn(name = "event_uid")
    private Event event;
    @OneToMany(mappedBy = "album")
    private List<AlbumMedia> albumMedia;

    public void addAlbumMediaLike(AlbumMedia albumMedia) {
        this.albumMedia.add(albumMedia);
        if (albumMedia.getAlbum() != this) {
            albumMedia.changeAlbum(this);
        }
    }
    public void changeEvent(Event event) {
        this.event = event;
        if (event.getAlbum() != this) {
            event.changeAlbum(this);
        }
    }

}
