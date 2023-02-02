package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember() {
        Member member = new Member("nyong@pang.party", false);
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getUid()).get();

        Assertions.assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        Assertions.assertThat(findMember.isSocial()).isEqualTo(member.isSocial());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    @Transactional
    public void basicCRUD() {
        System.out.println("=========START SAVE MEMBER=========");

        Member member1 = new Member("nyong@pang.party", false);
        Member member2 = new Member("test@pang.party", true);
        memberRepository.save(member1);
        memberRepository.save(member2);

        System.out.println("=========END SAVE MEMBER=========");

        System.out.println("=========START FIND MEMBER=========");

        // 단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getUid()).get();
        Member findMember2 = memberRepository.findById(member2.getUid()).get();
        Assertions.assertThat(findMember1).isEqualTo(member1);
        Assertions.assertThat(findMember2).isEqualTo(member2);

        System.out.println("=========END FIND MEMBER=========");

        System.out.println("=========START FIND MEMBER LIST=========");
        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND MEMBER LIST=========");

        System.out.println("=========START COUNT MEMBER=========");
        //카운트 검증
        long count = memberRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT MEMBER=========");

        System.out.println("=========START DELETE MEMBER=========");
        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        long deletedCount = memberRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE MEMBER=========");
    }
}
