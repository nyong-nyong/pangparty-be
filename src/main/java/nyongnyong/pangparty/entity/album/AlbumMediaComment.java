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
public class AlbumMediaComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private Long eventUid;
    private Long writerUid;
    private Long mediaUid;

    private String content;

    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

}
