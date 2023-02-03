package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.badge.MemberBadge;
import nyongnyong.pangparty.repository.badge.MemberBadgeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberBadgeRepositoryTest {

    @Autowired
    MemberBadgeRepository memberBadgeRepository;

    @Test
    public void testRollingPaper() {

        MemberBadge badge = new MemberBadge(LocalDateTime.now());
        MemberBadge savedBadge = memberBadgeRepository.save(badge);

        MemberBadge findBadge = memberBadgeRepository.findById(savedBadge.getUid()).get();

        Assertions.assertThat(findBadge.getUid()).isEqualTo(badge.getUid());
        Assertions.assertThat(findBadge.getAcquireTime()).isEqualTo(badge.getAcquireTime());
        Assertions.assertThat(findBadge).isEqualTo(badge);
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE MEMBER BADGE=========");
        MemberBadge memberBadge1 = new MemberBadge(LocalDateTime.now());
        MemberBadge memberBadge2 = new MemberBadge(LocalDateTime.now());
        memberBadgeRepository.save(memberBadge1);
        memberBadgeRepository.save(memberBadge2);
        System.out.println("=========END SAVE MEMBER BADGE=========");

        System.out.println("=========START FIND MEMBER BADGE=========");
        //단건 조회 검증
        MemberBadge findMemberBadge1 = memberBadgeRepository.findById(memberBadge1.getUid()).get();
        MemberBadge findMemberBadge2 = memberBadgeRepository.findById(memberBadge2.getUid()).get();
        assertThat(findMemberBadge1).isEqualTo(memberBadge1);
        assertThat(findMemberBadge2).isEqualTo(memberBadge2);
        System.out.println("=========START FIND MEMBER BADGE=========");


        System.out.println("=========START FIND MEMBER BADGE=========");
        //리스트 조회 검증
        List<MemberBadge> all = memberBadgeRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = memberBadgeRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        memberBadgeRepository.delete(memberBadge1);
        memberBadgeRepository.delete(memberBadge2);
        long deletedCount = memberBadgeRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
