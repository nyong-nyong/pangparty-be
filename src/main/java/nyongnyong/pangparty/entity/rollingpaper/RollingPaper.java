package nyongnyong.pangparty.entity.rollingpaper;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class RollingPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AtomicLong uid;

    private AtomicLong eventUid;
    private String bgColor;
}
