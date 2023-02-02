package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberAuthInfo;
import nyongnyong.pangparty.repository.member.MemberAuthInfoRepository;
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
public class MemberAuthInfoRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberAuthInfoRepository memberAuthInfoRepository;

    @Test
    public void testMemberAuthInfo() {
        System.out.println("=========START TEST MEMBER AUTH INFO=========");
        Member member = new Member("nyong@pang.party", false, LocalDateTime.now());
        memberRepository.save(member);

        System.out.println("MEMBER AUTH INFO ================");
        MemberAuthInfo memberAuthInfo = new MemberAuthInfo(member, "f1nd1ngn3m0", "07dbb6e6832da0841dd79701200e4b179f1a94a7b3dd26f612817f3c03117434", LocalDateTime.now());
        MemberAuthInfo savedMemberAuthInfo = memberAuthInfoRepository.save(memberAuthInfo);

        MemberAuthInfo findMemberAuthInfo = memberAuthInfoRepository.findById(savedMemberAuthInfo.getMemberUid()).get();

        Assertions.assertThat(findMemberAuthInfo.getMember()).isEqualTo(memberAuthInfo.getMember());
        Assertions.assertThat(findMemberAuthInfo.getSalt()).isEqualTo(memberAuthInfo.getSalt());
        Assertions.assertThat(findMemberAuthInfo.getPassword()).isEqualTo(memberAuthInfo.getPassword());
        Assertions.assertThat(findMemberAuthInfo.getUpdateTime()).isEqualTo(memberAuthInfo.getUpdateTime());
        Assertions.assertThat(findMemberAuthInfo).isEqualTo(memberAuthInfo);

        System.out.println("=========END TEST MEMBER AUTH INFO=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE MEMBER AUTH INFO=========");
        Member member1 = new Member("nyong@pang.party", false, LocalDateTime.now());
        Member member2 = new Member("test@pang.party", true, LocalDateTime.now());
        memberRepository.save(member1);
        memberRepository.save(member2);

        MemberAuthInfo memberAuthInfo1 = new MemberAuthInfo(member1, "f1nd1ngn3m0", "07dbb6e6832da0841dd79701200e4b179f1a94a7b3dd26f612817f3c03117434", LocalDateTime.now());
        MemberAuthInfo memberAuthInfo2 = new MemberAuthInfo(member2, "f1nd1ngd0ry", "11c150eb6c1b776f390be60a0a5933a2a2f8c0a0ce766ed92fea5bfd9313c8f6", LocalDateTime.now());
        memberAuthInfoRepository.save(memberAuthInfo1);
        memberAuthInfoRepository.save(memberAuthInfo2);

        System.out.println("=========END SAVE MEMBER AUTH INFO=========");

        System.out.println("=========START FIND MEMBER AUTH INFO=========");

        // 단건 조회 검증
        MemberAuthInfo findMemberAuthInfo1 = memberAuthInfoRepository.findById(memberAuthInfo1.getMemberUid()).get();
        MemberAuthInfo findMemberAuthInfo2 = memberAuthInfoRepository.findById(memberAuthInfo2.getMemberUid()).get();
        Assertions.assertThat(findMemberAuthInfo1).isEqualTo(memberAuthInfo1);
        Assertions.assertThat(findMemberAuthInfo2).isEqualTo(memberAuthInfo2);

        System.out.println("=========END FIND MEMBER AUTH INFO=========");

        System.out.println("=========START FIND MEMBER AUTH INFO LIST=========");
        //리스트 조회 검증
        List<MemberAuthInfo> all = memberAuthInfoRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND MEMBER AUTH INFO LIST=========");

        System.out.println("=========START COUNT MEMBER AUTH INFO=========");
        //카운트 검증
        long count = memberAuthInfoRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT MEMBER AUTH INFO=========");

        System.out.println("=========START DELETE MEMBER AUTH INFO=========");
        //삭제 검증
        memberAuthInfoRepository.delete(memberAuthInfo1);
        memberAuthInfoRepository.delete(memberAuthInfo2);
        long deletedCount = memberAuthInfoRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE MEMBER AUTH INFO=========");
    }
}
