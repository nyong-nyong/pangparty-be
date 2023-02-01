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
public class MemberAuthInfo {

    @Id
    private AtomicLong memberUid;

    private String salt;
    private String password;
    private LocalDateTime updateTime;

}
