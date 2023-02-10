package nyongnyong.pangparty.dto.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventIntroduceHashtags {
//    private Long uid;
    private String name;

    @Builder
    public EventIntroduceHashtags(String name) {
//        this.uid = uid;
        this.name = name;
    }
}
