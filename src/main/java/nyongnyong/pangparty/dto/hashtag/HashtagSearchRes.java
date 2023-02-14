package nyongnyong.pangparty.dto.hashtag;

import lombok.Data;
import nyongnyong.pangparty.entity.hashtag.Hashtag;

import java.time.LocalDateTime;

@Data
public class HashtagSearchRes {
    private Long hashtagUid;
    private String name;
    private LocalDateTime createTime;
    private int likes;
    private boolean isLiked;

    public HashtagSearchRes(Hashtag hashtag) {
        this.hashtagUid = hashtag.getUid();
        this.name = hashtag.getName();
        this.createTime = hashtag.getCreateTime();
    }
}
