package nyongnyong.pangparty.entity.badge;

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
public class MemberBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AtomicLong uid;

    private AtomicLong badgeUid;
    private AtomicLong memberUid;
    private LocalDateTime aquireTime;
}
