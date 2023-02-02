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
@ToString(of = {"memberUid", "phoneNo", "email", "birthday", "gender", "emailAuthTime", "phoneAuthTime", "address", "postalCode", "authority"})
public class MemberPersonal {

    @Id
    private Long memberUid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "member_uid")
    private Member member;
    private String phoneNo;
    private String email;
    private LocalDateTime birthday;
    private int gender;
    private LocalDateTime emailAuthTime;
    private LocalDateTime phoneAuthTime;
    private String address;
    private String postalCode;
    private String authority;

}
