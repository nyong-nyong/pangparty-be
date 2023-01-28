package nyongnyong.pangparty.album.domain;

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
    private AtomicLong uid;

    private AtomicLong eventUid;
    private AtomicLong writerUid;
    private AtomicLong mediaUid;

    private String content;

    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

}
