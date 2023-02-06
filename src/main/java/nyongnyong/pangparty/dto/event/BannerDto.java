package nyongnyong.pangparty.dto.event;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class BannerDto {
    @Id
    private Long uid;
    @NonNull
    private Long memberUid;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BannerDto(Long uid, Long memberUid, String content, LocalDateTime createTime, LocalDateTime startTime, LocalDateTime endTime) {
        this.uid = uid;
        this.memberUid = memberUid;
        this.content = content;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
