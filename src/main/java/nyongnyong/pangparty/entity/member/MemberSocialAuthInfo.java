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
@ToString(of = {"memberUid", "socialType", "externalId", "updateTime"})
public class MemberSocialAuthInfo {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    private Member member;
    private boolean socialType;
    private boolean externalId;
    private LocalDateTime updateTime;

}
