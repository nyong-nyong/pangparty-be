package nyongnyong.pangparty.entity.rollingpaper;

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
public class RollingPaperSticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AtomicLong uid;

    private AtomicLong paperUid;
    private AtomicLong writerUid;
    private LocalDateTime createTime;
    private String stickerUrl;
    private String locX;
    private String locY;
    private String zIndex;
    private String angle;
    private String scale;
}
