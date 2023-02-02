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
@ToString(of = {"memberUid", "phoneNo", "email", "birthday", "gender", "address", "postalCode", "emailAuthTime", "phoneAuthTime", "joinTime", "updateTime", "authority"})
public class MemberPersonal {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    private Member member;
    private String phoneNo;
    private String email;
    private LocalDateTime birthday;
    private int gender;
    private String address;
    private String postalCode;
    private LocalDateTime emailAuthTime;
    private LocalDateTime phoneAuthTime;
    private LocalDateTime join_time;
    private LocalDateTime update_time;
    private String authority;

}
