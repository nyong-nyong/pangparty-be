package nyongnyong.pangparty.entity.event;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberProfile;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "addTime"})
public class EventTarget implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private MemberProfile targetMemberProfile;

    private LocalDateTime addTime;

    @Builder
    public EventTarget(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public void changeEvent(Event event) {
        this.event = event;
        // TODO Event에 participant 추가 시, 예외 처리 코드 필요
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
