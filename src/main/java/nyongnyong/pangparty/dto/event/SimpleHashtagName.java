package nyongnyong.pangparty.dto.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.hashtag.Hashtag;

@Data
@NoArgsConstructor
public class SimpleHashtagName {
    private String name;

    @Builder
    public SimpleHashtagName(String name) {
        this.name = name;
    }

    public SimpleHashtagName(Hashtag hashtag) {
        this.name = hashtag.getName();
    }

}
