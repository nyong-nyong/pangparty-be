package nyongnyong.pangparty.entity.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "joinTime"})
public class EventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    private LocalDateTime joinTime;

    public void changeEvent(Event event) {
        this.event = event;
        // TODO Event에 participant 추가 시, 예외 처리 코드 필요
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
