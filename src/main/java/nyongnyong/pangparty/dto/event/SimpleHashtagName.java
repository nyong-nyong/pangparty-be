package nyongnyong.pangparty.dto.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleHashtagName {
    private String name;

    @Builder
    public SimpleHashtagName(String name) {
        this.name = name;
    }
}
