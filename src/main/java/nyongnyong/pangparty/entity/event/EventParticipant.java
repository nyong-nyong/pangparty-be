package nyongnyong.pangparty.entity.event;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class EventParticipant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    @ToString.Exclude
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    private LocalDateTime joinTime;

    @Builder
    public EventParticipant(Event event, Member member, LocalDateTime joinTime) {
        this.event = event;
        this.member = member;
        this.joinTime = joinTime;
    }

    public EventParticipant(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    public void changeEvent(Event event) {
        this.event = event;
        // TODO Event에 participant 추가 시, 예외 처리 코드 필요
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
