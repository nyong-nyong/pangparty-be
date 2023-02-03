package nyongnyong.pangparty.entity.member;

import lombok.*;
import nyongnyong.pangparty.common.AuthorityType;

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
    @Column(unique = true)
    private String phoneNo;
    @Column(unique = true)
    private String email;
    private LocalDateTime birthday;
    private int gender;
    private String address;
    private String postalCode;
    private LocalDateTime emailAuthTime;
    private LocalDateTime phoneAuthTime;
    private LocalDateTime joinTime;
    private LocalDateTime updateTime;

    @Enumerated(EnumType.STRING)
    private AuthorityType authority;

    @Builder
    public MemberPersonal(Member member, String phoneNo, String email, LocalDateTime birthday, int gender, String address, String postalCode, LocalDateTime emailAuthTime, LocalDateTime phoneAuthTime, LocalDateTime joinTime, LocalDateTime updateTime, AuthorityType authority) {
        this.member = member;
        this.phoneNo = phoneNo;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.postalCode = postalCode;
        this.emailAuthTime = emailAuthTime;
        this.phoneAuthTime = phoneAuthTime;
        this.joinTime = joinTime;
        this.updateTime = updateTime;
        this.authority = authority;
    }
}
