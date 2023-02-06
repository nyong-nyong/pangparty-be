package nyongnyong.pangparty.dto.hashtag;

import lombok.*;
import nyongnyong.pangparty.entity.hashtag.Hashtag;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HashtagDto {

    @Id
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
