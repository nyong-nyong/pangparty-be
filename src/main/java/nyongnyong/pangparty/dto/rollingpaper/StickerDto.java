package nyongnyong.pangparty.dto.rollingpaper;

import lombok.*;
import nyongnyong.pangparty.entity.rollingpaper.Sticker;

@Data
@ToString
public class StickerDto {
    private final Long uid;
    private final String stickerUrl;
//    private final String metaTag;

    public StickerDto(final Sticker sticker) {
        this.uid = sticker.getUid();
        this.stickerUrl = sticker.getStickerUrl();
//        this.metaTag = sticker.getMetaTag();
    }
}
