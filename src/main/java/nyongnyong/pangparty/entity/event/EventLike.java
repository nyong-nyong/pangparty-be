package nyongnyong.pangparty.entity.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"uid", "likeTime"})
public class EventLike {

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

    public EventLike(LocalDateTime likeTime) {
        this.likeTime = likeTime;
    }

    public void changeEvent(Event event) {
        this.event = event;
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
