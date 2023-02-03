package nyongnyong.pangparty.dto.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "addTime"})
public class EventHashtagDto {
    private Long uid;
    private LocalDateTime addTime;



}
