package nyongnyong.pangparty.dto.hashtag;

import lombok.Builder;
import nyongnyong.pangparty.entity.hashtag.Hashtag;

import java.time.LocalDateTime;

public class HashtagDto {

    private Long uid;
    private String name;
    private LocalDateTime createTime;

    @Builder
    public HashtagDto(Hashtag hashtag) {
        this.uid = hashtag.getUid();
        this.name = hashtag.getName();
        this.createTime = hashtag.getCreateTime();
    }
}
