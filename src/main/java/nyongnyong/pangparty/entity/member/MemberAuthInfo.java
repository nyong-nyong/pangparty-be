package nyongnyong.pangparty.entity.member;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"memberUid", "salt", "password", "updateTime"})
public class MemberAuthInfo implements Serializable {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    private Member member;
    private String salt;
    private String password;
    private LocalDateTime updateTime;

    @Builder
    public MemberAuthInfo(Member member, String salt, String password, LocalDateTime updateTime) {
        this.member = member;
        this.salt = salt;
        this.password = password;
        this.updateTime = updateTime;
    }
}
