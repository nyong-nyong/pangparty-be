package nyongnyong.pangparty.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class MemberSetting {

    @Id
    private AtomicLong memberUid;

    private boolean alarmOnAll;
    private boolean alarmOnFollowing;
    private boolean alarmOnBadge;
    private boolean alarmOnEventSchedule;
    private boolean alarmOnFriendEventCreate;

}
