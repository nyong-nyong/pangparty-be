package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.member.MemberSetting;
import nyongnyong.pangparty.repository.member.MemberSettingRepository;
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
public class MemberSettingRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberSettingRepository memberSettingRepository;

    @Test
    public void testMemberSetting() {
        System.out.println("=========START TEST MEMBER SETTING=========");
        Member member = new Member("nyong@pang.party", false, LocalDateTime.now());
        memberRepository.save(member);

        MemberSetting memberSetting = new MemberSetting(member, true, true, true, true, true);
        MemberSetting savedMemberSetting = memberSettingRepository.save(memberSetting);

        MemberSetting findMemberSetting = memberSettingRepository.findById(savedMemberSetting.getMemberUid()).get();

        Assertions.assertThat(findMemberSetting.getMember()).isEqualTo(memberSetting.getMember());
        Assertions.assertThat(findMemberSetting.isAlarmOnAll()).isEqualTo(memberSetting.isAlarmOnAll());
        Assertions.assertThat(findMemberSetting.isAlarmOnFollowing()).isEqualTo(memberSetting.isAlarmOnFollowing());
        Assertions.assertThat(findMemberSetting.isAlarmOnBadge()).isEqualTo(memberSetting.isAlarmOnBadge());
        Assertions.assertThat(findMemberSetting.isAlarmOnEventSchedule()).isEqualTo(memberSetting.isAlarmOnEventSchedule());
        Assertions.assertThat(findMemberSetting.isAlarmOnFriendEventCreate()).isEqualTo(memberSetting.isAlarmOnFriendEventCreate());
        Assertions.assertThat(findMemberSetting).isEqualTo(memberSetting);

        System.out.println("=========END TEST MEMBER SETTING=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE MEMBER SETTING=========");
        Member member1 = new Member("nyong@pang.party", false, LocalDateTime.now());
        Member member2 = new Member("test@pang.party", true, LocalDateTime.now());
        memberRepository.save(member1);
        memberRepository.save(member2);

        MemberSetting memberSetting1 = new MemberSetting(member1, true, true, true, true, true);
        MemberSetting memberSetting2 = new MemberSetting(member2, false, true, false, true, false);
        memberSettingRepository.save(memberSetting1);
        memberSettingRepository.save(memberSetting2);

        System.out.println("=========END SAVE MEMBER SETTING=========");

        System.out.println("=========START FIND MEMBER SETTING=========");

        // 단건 조회 검증
        MemberSetting findMemberSetting1 = memberSettingRepository.findById(memberSetting1.getMemberUid()).get();
        MemberSetting findMemberSetting2 = memberSettingRepository.findById(memberSetting2.getMemberUid()).get();
        Assertions.assertThat(findMemberSetting1).isEqualTo(memberSetting1);
        Assertions.assertThat(findMemberSetting2).isEqualTo(memberSetting2);

        System.out.println("=========END FIND MEMBER SETTING=========");

        System.out.println("=========START FIND MEMBER SETTING LIST=========");
        //리스트 조회 검증
        List<MemberSetting> all = memberSettingRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND MEMBER SETTING LIST=========");

        System.out.println("=========START COUNT MEMBER SETTING=========");
        //카운트 검증
        long count = memberSettingRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT MEMBER SETTING=========");

        System.out.println("=========START DELETE MEMBER SETTING=========");
        //삭제 검증
        memberSettingRepository.delete(memberSetting1);
        memberSettingRepository.delete(memberSetting2);
        long deletedCount = memberSettingRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE MEMBER SETTING=========");
    }
}
