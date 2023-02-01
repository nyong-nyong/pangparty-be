package nyongnyong.pangparty.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class MemberSocialAuthInfo {

    @Id
    private AtomicLong memberUid;

    private boolean socialType;
    private boolean externalId;
    private LocalDateTime updateTime;

}
