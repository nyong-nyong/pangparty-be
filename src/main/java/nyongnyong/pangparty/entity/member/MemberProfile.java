package nyongnyong.pangparty.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class MemberProfile {

    @Id
    private AtomicLong memberUid;

    private String id;
    private String name;
    private String img_url;
    private String introduction;
    private LocalDateTime joinTime;
    private LocalDateTime updateTime;

}
