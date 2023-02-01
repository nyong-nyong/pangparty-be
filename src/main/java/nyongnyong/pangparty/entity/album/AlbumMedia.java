package nyongnyong.pangparty.entity.album;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class AlbumMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private Long eventUid;

    private String mediaUrl;
    private String extension;

    private LocalDateTime uploadTime;
    private LocalDateTime takenTime;
    private String takenLat;
    private String takenLng;

}
