package nyongnyong.pangparty.dto.rollingpaper;

import lombok.Data;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.rollingpaper.Sticker;

@Data
@NoArgsConstructor
public class StickerDto {
    private Long uid;
    private String stickerUrl;
    private String metaTag;

    public StickerDto(Sticker sticker) {
        this.uid = sticker.getUid();
        this.stickerUrl = sticker.getStickerUrl();
        this.metaTag = sticker.getMetaTag();
    }
    
    public Sticker toEntity() {
        return Sticker.builder().stickerUrl(stickerUrl).metaTag(metaTag).build();
    }
}
