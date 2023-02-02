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
@ToString(of = {"memberUid", "salt", "password", "updateTime"})
public class MemberAuthInfo {

    @Id
    private Long memberUid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "member_uid")
    private Member member;
    private String salt;
    private String password;
    private LocalDateTime updateTime;

}
