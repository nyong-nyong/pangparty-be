package nyongnyong.pangparty.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
public class MemberPersonal {

    @Id
    private AtomicLong memberUid;

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
