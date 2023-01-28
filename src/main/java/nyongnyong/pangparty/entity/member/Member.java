package nyongnyong.pangparty.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicLong;

@Entity     // JPA 영속성 객체 등록
@Getter     // setter 사용 금지
@NoArgsConstructor      // JPA Entity는 기본 생성자 필요
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AtomicLong uid;

    private String email;
    private boolean is_social;
    
}
