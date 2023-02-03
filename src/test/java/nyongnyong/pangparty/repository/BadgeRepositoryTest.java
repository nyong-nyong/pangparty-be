package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.badge.Badge;
import nyongnyong.pangparty.repository.badge.BadgeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BadgeRepositoryTest {

    @Autowired
    BadgeRepository badgeRepository;

    @Test
    public void testRollingPaper() {

        Badge badge = new Badge("name", "imgurl", "condition");
        Badge savedBadge = badgeRepository.save(badge);

        Badge findBadge = badgeRepository.findById(savedBadge.getUid()).get();

        Assertions.assertThat(findBadge.getUid()).isEqualTo(badge.getUid());
        Assertions.assertThat(findBadge.getBadgeName()).isEqualTo(badge.getBadgeName());
        Assertions.assertThat(findBadge.getImgUrl()).isEqualTo(badge.getImgUrl());
        Assertions.assertThat(findBadge.getBadgeCondition()).isEqualTo(badge.getBadgeCondition());
        Assertions.assertThat(findBadge).isEqualTo(badge);
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE BADGE=========");
        Badge badge1 = new Badge("naver", "naver.com", "cond1");
        Badge badge2 = new Badge("yahoo", "yahoo.com", "cond2");
        badgeRepository.save(badge1);
        badgeRepository.save(badge2);
        System.out.println("=========END SAVE BADGE=========");

        System.out.println("=========START FIND BADGE=========");
        //단건 조회 검증
        Badge findBadge1 = badgeRepository.findById(badge1.getUid()).get();
        Badge findBadge2 = badgeRepository.findById(badge2.getUid()).get();
        assertThat(findBadge1).isEqualTo(badge1);
        assertThat(findBadge2).isEqualTo(badge2);
        System.out.println("=========START FIND BADGE=========");


        System.out.println("=========START FIND BADGE=========");
        //리스트 조회 검증
        List<Badge> all = badgeRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = badgeRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        badgeRepository.delete(badge1);
        badgeRepository.delete(badge2);
        long deletedCount = badgeRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
