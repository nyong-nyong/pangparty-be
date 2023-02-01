package nyongnyong.pangparty.entity.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.album.AlbumMedia;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private Long hostUid;
    private String eventName;
    private String introduction;
    private String imgUrl;
    private LocalDate dDay;

    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime partyTime;

    private boolean isPrivate;
    private boolean hasRollingPaper;

    @OneToMany
    private List<AlbumMedia> albumMedia;

    private boolean hasAlbum;
    private boolean hasPlaylist;
    private boolean hasFunding;
}
