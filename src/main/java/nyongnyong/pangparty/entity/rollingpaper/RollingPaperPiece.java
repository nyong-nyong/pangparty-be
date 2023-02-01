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
public class RollingPaperPiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AtomicLong uid;

    private AtomicLong paperUid;
    private AtomicLong writerUid;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String content;
    private String bgColor;
    private String bgImgUrl;
    private String bgImgAlt;
    private String fontFamily;
    private String textColor;
    private String textAlign;
}
