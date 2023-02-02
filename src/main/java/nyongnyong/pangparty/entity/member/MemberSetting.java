package nyongnyong.pangparty.entity.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"memberUid", "alarmOnAll", "alarmOnFollowing", "alarmOnBadge", "alarmOnEventSchedule", "alarmOnFriendEventCreate"})
public class MemberSetting {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    private Member member;
    private boolean alarmOnAll;
    private boolean alarmOnFollowing;
    private boolean alarmOnBadge;
    private boolean alarmOnEventSchedule;
    private boolean alarmOnFriendEventCreate;

}
