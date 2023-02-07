package nyongnyong.pangparty.entity.rollingpaper;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Sticker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String stickerUrl;
    private String metaTag;

    @Builder
    public Sticker(String stickerUrl, String metaTag) {
        this.stickerUrl = stickerUrl;
        this.metaTag = metaTag;
    }
}
