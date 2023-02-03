package nyongnyong.pangparty.entity.member;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity     // JPA 영속성 객체 등록
@Getter     // setter 사용 금지
@NoArgsConstructor(access = AccessLevel.PROTECTED)      // 기본 생성자 막고 싶은데, JPA 스팩상 PROTECTED로 열어두어야 함
@ToString(of = {"uid", "email", "isSocial", "withdrawTime"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(unique = true)
    private String email;
    private boolean isSocial;
    private LocalDateTime withdrawTime;

    @Builder
    public Member(String email, boolean isSocial, LocalDateTime withdrawTime) {
        this.email = email;
        this.isSocial = isSocial;
        this.withdrawTime = withdrawTime;
    }

}
