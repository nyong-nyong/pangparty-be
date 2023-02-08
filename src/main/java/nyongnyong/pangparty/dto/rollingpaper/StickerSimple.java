package nyongnyong.pangparty.dto.rollingpaper;

import lombok.*;

@Data
@ToString
public class StickerSimple {
    private final Long uid;
    private final String stickerUrl;
//    private final String metaTag;

    public StickerSimple(final nyongnyong.pangparty.entity.rollingpaper.Sticker sticker) {
        this.uid = sticker.getUid();
        this.stickerUrl = sticker.getStickerUrl();
//        this.metaTag = sticker.getMetaTag();
    }
}
