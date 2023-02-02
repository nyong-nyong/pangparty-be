package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.common.SocialAuthType;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberSocialAuthInfo;
import nyongnyong.pangparty.repository.member.MemberSocialAuthInfoRepository;
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
public class MemberSocialAuthInfoRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberSocialAuthInfoRepository memberSocialAuthInfoRepository;

    @Test
    public void testMemberSocialAuthInfo() {
        System.out.println("=========START TEST MEMBER SOCIAL AUTH INFO=========");
        Member member = new Member("nyong@pang.party", true, LocalDateTime.now());
        memberRepository.save(member);

        MemberSocialAuthInfo memberSocialAuthInfo = new MemberSocialAuthInfo(member, SocialAuthType.NAVER, "oauthexternalID", LocalDateTime.now());
        MemberSocialAuthInfo savedMemberSocialAuthInfo = memberSocialAuthInfoRepository.save(memberSocialAuthInfo);

        MemberSocialAuthInfo findMemberSocialAuthInfo = memberSocialAuthInfoRepository.findById(savedMemberSocialAuthInfo.getMemberUid()).get();

        Assertions.assertThat(findMemberSocialAuthInfo.getMember()).isEqualTo(memberSocialAuthInfo.getMember());
        Assertions.assertThat(findMemberSocialAuthInfo.getSocialType()).isEqualTo(memberSocialAuthInfo.getSocialType());
        Assertions.assertThat(findMemberSocialAuthInfo.getExternalId()).isEqualTo(memberSocialAuthInfo.getExternalId());
        Assertions.assertThat(findMemberSocialAuthInfo.getUpdateTime()).isEqualTo(memberSocialAuthInfo.getUpdateTime());
        Assertions.assertThat(findMemberSocialAuthInfo.getUpdateTime()).isEqualTo(memberSocialAuthInfo.getUpdateTime());
        Assertions.assertThat(findMemberSocialAuthInfo).isEqualTo(memberSocialAuthInfo);

        System.out.println("=========END TEST MEMBER SOCIAL AUTH INFO=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE MEMBER SOCIAL AUTH INFO=========");
        Member member1 = new Member("nyong@pang.party", true, LocalDateTime.now());
        Member member2 = new Member("test@pang.party", true, LocalDateTime.now());
        memberRepository.save(member1);
        memberRepository.save(member2);

        MemberSocialAuthInfo memberSocialAuthInfo1 = new MemberSocialAuthInfo(member1, SocialAuthType.NAVER, "oauthexternalID", LocalDateTime.now());
        MemberSocialAuthInfo memberSocialAuthInfo2 = new MemberSocialAuthInfo(member2, SocialAuthType.GOOGLE, "goooooooogle", LocalDateTime.now());
        memberSocialAuthInfoRepository.save(memberSocialAuthInfo1);
        memberSocialAuthInfoRepository.save(memberSocialAuthInfo2);

        System.out.println("=========END SAVE MEMBER SOCIAL AUTH INFO=========");

        System.out.println("=========START FIND MEMBER SOCIAL AUTH INFO=========");

        // 단건 조회 검증
        MemberSocialAuthInfo findMemberSocialAuthInfo1 = memberSocialAuthInfoRepository.findById(memberSocialAuthInfo1.getMemberUid()).get();
        MemberSocialAuthInfo findMemberSocialAuthInfo2 = memberSocialAuthInfoRepository.findById(memberSocialAuthInfo2.getMemberUid()).get();
        Assertions.assertThat(findMemberSocialAuthInfo1).isEqualTo(memberSocialAuthInfo1);
        Assertions.assertThat(findMemberSocialAuthInfo2).isEqualTo(memberSocialAuthInfo2);

        System.out.println("=========END FIND MEMBER SOCIAL AUTH INFO=========");

        System.out.println("=========START FIND MEMBER SOCIAL AUTH INFO LIST=========");
        //리스트 조회 검증
        List<MemberSocialAuthInfo> all = memberSocialAuthInfoRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND MEMBER SOCIAL AUTH INFO LIST=========");

        System.out.println("=========START COUNT MEMBER SOCIAL AUTH INFO=========");
        //카운트 검증
        long count = memberSocialAuthInfoRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT MEMBER SOCIAL AUTH INFO=========");

        System.out.println("=========START DELETE MEMBER SOCIAL AUTH INFO=========");
        //삭제 검증
        memberSocialAuthInfoRepository.delete(memberSocialAuthInfo1);
        memberSocialAuthInfoRepository.delete(memberSocialAuthInfo2);
        long deletedCount = memberSocialAuthInfoRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE MEMBER SOCIAL AUTH INFO=========");
    }
}
