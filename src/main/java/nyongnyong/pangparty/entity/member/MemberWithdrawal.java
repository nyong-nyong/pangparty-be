package nyongnyong.pangparty.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class MemberWithdrawal {

    @Id
    private AtomicLong memberUid;

    private LocalDateTime withdrawTime;

}
