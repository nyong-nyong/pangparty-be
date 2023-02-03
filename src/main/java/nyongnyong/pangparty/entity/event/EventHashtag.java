package nyongnyong.pangparty.entity.event;

import lombok.*;
import nyongnyong.pangparty.entity.hashtag.Hashtag;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "addTime"})
public class EventHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_uid")
    private Hashtag hashtag;

    private LocalDateTime addTime;

    @Builder
    public EventHashtag(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public void changeEvent(Event event) {
        this.event = event;
    }

    public void changeHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }
}
