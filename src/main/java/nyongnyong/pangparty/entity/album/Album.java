package nyongnyong.pangparty.entity.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.event.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid"})
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne
    @JoinColumn(name = "event_uid")
    private Event event;

    @OneToMany(mappedBy = "album")
    private List<AlbumMedia> albumMedia;

    public void addAlbumMedia(AlbumMedia albumMedia) {
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
