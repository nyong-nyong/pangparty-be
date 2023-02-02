package nyongnyong.pangparty.entity.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name", "imgUrl", "introduction", "joinTime", "updateTime"})
public class MemberProfile {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    private Member member;
    private String id;
    private String name;
    private String imgUrl;
    private String introduction;
    private LocalDateTime joinTime;
    private LocalDateTime updateTime;

}