package nyongnyong.pangparty.entity.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity     // JPA 영속성 객체 등록
@Getter     // setter 사용 금지
@NoArgsConstructor(access = AccessLevel.PROTECTED)      // 기본 생성자 막고 싶은데, JPA 스팩상 PROTECTED로 열어두어야 함
@ToString(of = {"uid", "email", "is_social", "withdrawTime"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String email;
    private boolean is_social;
    private LocalDateTime withdrawTime;

    public Member(String email, boolean is_social) {
        this.email = email;
        this.is_social = is_social;
    }

}
