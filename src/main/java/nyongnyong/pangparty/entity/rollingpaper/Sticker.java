package nyongnyong.pangparty.entity.rollingpaper;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "stickerUrl", "metaTag"})
public class Sticker {
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
