package nyongnyong.pangparty.entity.event;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "likeTime"})
public class EventLike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    private LocalDateTime likeTime;

//    @Builder
    public EventLike(LocalDateTime likeTime) {
        this.likeTime = likeTime;
    }

    @Builder
    public EventLike(Event event, Member member, LocalDateTime likeTime) {
        this.event = event;
        this.member = member;
        this.likeTime = likeTime;
    }

    public void changeEvent(Event event) {
        this.event = event;
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
