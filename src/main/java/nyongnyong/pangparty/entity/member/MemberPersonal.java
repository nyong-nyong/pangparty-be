package nyongnyong.pangparty.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import nyongnyong.pangparty.common.AuthorityType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberPersonal implements Serializable {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    @JsonIgnore
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
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime joinTime;
    @LastModifiedDate
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
