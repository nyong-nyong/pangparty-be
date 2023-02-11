package nyongnyong.pangparty.dto.event;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class EventCreateRes {
    Long eventUid;

    public EventCreateRes(Long eventUid) {
        this.eventUid = eventUid;
    }
}
