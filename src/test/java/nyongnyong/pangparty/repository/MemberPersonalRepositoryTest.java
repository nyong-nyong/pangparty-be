package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.common.AuthorityType;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberPersonal;
import nyongnyong.pangparty.repository.member.MemberPersonalRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class MemberPersonalRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberPersonalRepository memberPersonalRepository;

    @Test
    public void testMemberPersonal() {
        System.out.println("=========START TEST MEMBER PERSONAL=========");
        Member member = new Member("nyong@pang.party", false, LocalDateTime.now());
        memberRepository.save(member);

        System.out.println("MEMBER PERSONAL ================");
        MemberPersonal memberPersonal = new MemberPersonal(member, "01012341234", "nyong@pang.party", LocalDateTime.now(), 0, "서울시 역삼동 어딘가", "10001", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), null, AuthorityType.ADMIN);
        MemberPersonal savedMemberPersonal = memberPersonalRepository.save(memberPersonal);

        MemberPersonal findMemberPersonal = memberPersonalRepository.findById(savedMemberPersonal.getMemberUid()).get();

        System.out.println(savedMemberPersonal);
        System.out.println(findMemberPersonal);

        Assertions.assertThat(findMemberPersonal.getMember()).isEqualTo(memberPersonal.getMember());
        Assertions.assertThat(findMemberPersonal.getPhoneNo()).isEqualTo(memberPersonal.getPhoneNo());
        Assertions.assertThat(findMemberPersonal.getEmail()).isEqualTo(memberPersonal.getEmail());
        Assertions.assertThat(findMemberPersonal.getBirthday()).isEqualTo(memberPersonal.getBirthday());
        Assertions.assertThat(findMemberPersonal.getGender()).isEqualTo(memberPersonal.getGender());
        Assertions.assertThat(findMemberPersonal.getAddress()).isEqualTo(memberPersonal.getAddress());
        Assertions.assertThat(findMemberPersonal.getPostalCode()).isEqualTo(memberPersonal.getPostalCode());
        Assertions.assertThat(findMemberPersonal.getEmailAuthTime()).isEqualTo(memberPersonal.getEmailAuthTime());
        Assertions.assertThat(findMemberPersonal.getPhoneAuthTime()).isEqualTo(memberPersonal.getPhoneAuthTime());
        Assertions.assertThat(findMemberPersonal.getJoinTime()).isEqualTo(memberPersonal.getJoinTime());
        Assertions.assertThat(findMemberPersonal.getUpdateTime()).isEqualTo(memberPersonal.getUpdateTime());
        Assertions.assertThat(findMemberPersonal.getAuthority()).isEqualTo(memberPersonal.getAuthority());
        Assertions.assertThat(findMemberPersonal).isEqualTo(memberPersonal);
        memberPersonalRepository.delete(memberPersonal);

        System.out.println("=========END TEST MEMBER PERSONAL=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE MEMBER PERSONAL=========");
        Member member1 = new Member("nyong@pang.party", false, LocalDateTime.now());
        Member member2 = new Member("test@pang.party", true, LocalDateTime.now());
        memberRepository.save(member1);
        memberRepository.save(member2);

        MemberPersonal memberPersonal1 = new MemberPersonal(member1, "01012341234", "nyong@pang.party", LocalDateTime.now(), 0, "서울시 역삼동 어딘가", "10001", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), null, AuthorityType.ADMIN);
        MemberPersonal memberPersonal2 = new MemberPersonal(member2, "01009870987", "test@pang.party", LocalDateTime.now(), 0, "서울시 서초구 어딘가", "20002", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), null, AuthorityType.MEMBER);
        memberPersonalRepository.save(memberPersonal1);
        memberPersonalRepository.save(memberPersonal2);

        System.out.println("=========END SAVE MEMBER PERSONAL=========");

        System.out.println("=========START FIND MEMBER PERSONAL=========");

        // 단건 조회 검증
        MemberPersonal findMemberPersonal1 = memberPersonalRepository.findById(memberPersonal1.getMemberUid()).get();
        MemberPersonal findMemberPersonal2 = memberPersonalRepository.findById(memberPersonal2.getMemberUid()).get();
        Assertions.assertThat(findMemberPersonal1).isEqualTo(memberPersonal1);
        Assertions.assertThat(findMemberPersonal2).isEqualTo(memberPersonal2);

        System.out.println("=========END FIND MEMBER PERSONAL=========");

        System.out.println("=========START FIND MEMBER PERSONAL LIST=========");
        //리스트 조회 검증
        List<MemberPersonal> all = memberPersonalRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND MEMBER PERSONAL LIST=========");

        System.out.println("=========START COUNT MEMBER PERSONAL=========");
        //카운트 검증
        long count = memberPersonalRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT MEMBER PERSONAL=========");

        System.out.println("=========START DELETE MEMBER PERSONAL=========");
        //삭제 검증
        memberPersonalRepository.delete(memberPersonal1);
        memberPersonalRepository.delete(memberPersonal2);
        long deletedCount = memberPersonalRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE MEMBER PERSONAL=========");
    }
}
