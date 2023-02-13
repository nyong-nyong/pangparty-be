package nyongnyong.pangparty.dto.hashtag;

import lombok.Data;
import nyongnyong.pangparty.entity.hashtag.Hashtag;

import java.time.LocalDateTime;

@Data
public class HashtagSearchRes {
    private Long uid;
    private String name;
    private LocalDateTime createTime;
    private int likes;
    private boolean isLiked;

    public HashtagSearchRes(Hashtag hashtag) {
        this.uid = hashtag.getUid();
        this.name = hashtag.getName();
        this.createTime = hashtag.getCreateTime();
    }
}
