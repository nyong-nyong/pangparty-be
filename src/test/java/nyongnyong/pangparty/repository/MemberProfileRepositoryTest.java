package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberProfile;
import nyongnyong.pangparty.repository.member.MemberProfileRepository;
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
public class MemberProfileRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberProfileRepository memberProfileRepository;

    @Test
    public void testMemberProfile() {
        System.out.println("=========START TEST MEMBER PROFILE=========");
        Member member = new Member("nyong@pang.party", false, LocalDateTime.now());
        memberRepository.save(member);

        MemberProfile memberProfile = new MemberProfile(member, "pang", "팡팡팡", "https://cdn.salgoonews.com/news/photo/202201/15534_38635_325.jpg", "소개입니다", LocalDateTime.now());
        MemberProfile savedMemberProfile = memberProfileRepository.save(memberProfile);

        MemberProfile findMemberProfile = memberProfileRepository.findById(savedMemberProfile.getMemberUid()).get();

        Assertions.assertThat(findMemberProfile.getMember()).isEqualTo(memberProfile.getMember());
        Assertions.assertThat(findMemberProfile.getId()).isEqualTo(memberProfile.getId());
        Assertions.assertThat(findMemberProfile.getName()).isEqualTo(memberProfile.getName());
        Assertions.assertThat(findMemberProfile.getImgUrl()).isEqualTo(memberProfile.getImgUrl());
        Assertions.assertThat(findMemberProfile.getIntroduction()).isEqualTo(memberProfile.getIntroduction());
        Assertions.assertThat(findMemberProfile.getUpdateTime()).isEqualTo(memberProfile.getUpdateTime());
        Assertions.assertThat(findMemberProfile).isEqualTo(memberProfile);

        System.out.println("=========END TEST MEMBER PROFILE=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE MEMBER PROFILE=========");
        Member member1 = new Member("nyong@pang.party", false, LocalDateTime.now());
        Member member2 = new Member("test@pang.party", true, LocalDateTime.now());
        memberRepository.save(member1);
        memberRepository.save(member2);

        MemberProfile memberProfile1 = new MemberProfile(member1, "pang", "팡팡팡", "https://cdn.salgoonews.com/news/photo/202201/15534_38635_325.jpg", "소개입니다", LocalDateTime.now());
        MemberProfile memberProfile2 = new MemberProfile(member2, "example", "예시예시", "https://cdn.salgoonews.com/news/photo/202201/15534_38635_325.jpg", "소개입니다", LocalDateTime.now());
        memberProfileRepository.save(memberProfile1);
        memberProfileRepository.save(memberProfile2);

        System.out.println("=========END SAVE MEMBER PROFILE=========");

        System.out.println("=========START FIND MEMBER PROFILE=========");

        // 단건 조회 검증
        MemberProfile findMemberProfile1 = memberProfileRepository.findById(memberProfile1.getMemberUid()).get();
        MemberProfile findMemberProfile2 = memberProfileRepository.findById(memberProfile2.getMemberUid()).get();
        Assertions.assertThat(findMemberProfile1).isEqualTo(memberProfile1);
        Assertions.assertThat(findMemberProfile2).isEqualTo(memberProfile2);

        System.out.println("=========END FIND MEMBER PROFILE=========");

        System.out.println("=========START FIND MEMBER PROFILE LIST=========");
        //리스트 조회 검증
        List<MemberProfile> all = memberProfileRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND MEMBER PROFILE LIST=========");

        System.out.println("=========START COUNT MEMBER PROFILE=========");
        //카운트 검증
        long count = memberProfileRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT MEMBER PROFILE=========");

        System.out.println("=========START DELETE MEMBER PROFILE=========");
        //삭제 검증
        memberProfileRepository.delete(memberProfile1);
        memberProfileRepository.delete(memberProfile2);
        long deletedCount = memberProfileRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE MEMBER PROFILE=========");
    }
}
