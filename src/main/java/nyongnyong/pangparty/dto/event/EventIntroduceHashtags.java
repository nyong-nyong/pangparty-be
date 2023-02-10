package nyongnyong.pangparty.dto.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventIntroduceHashtags {
    private String name;

    @Builder
    public EventIntroduceHashtags(String name) {
        this.name = name;
    }
}
