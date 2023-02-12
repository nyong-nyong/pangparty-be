package nyongnyong.pangparty.dto.event;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BannerRes {
    private Long bannerUid;
    private String id;
    private String content;

    public BannerRes(Long bannerUid, String id, String content) {
        this.bannerUid = bannerUid;
        this.id = id;
        this.content = content;
    }
}
