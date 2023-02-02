package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.hashtag.MemberHashtag;
import nyongnyong.pangparty.repository.hashtag.MemberHashtagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class MemberHashtagRepositoryTest {

    @Autowired
    MemberHashtagRepository memberHashtagRepository;

    @Test
    public void testMemberHashtag() {
        System.out.println("=========START TEST MEMBER HASHTAG=========");

        MemberHashtag memberHashtag = new MemberHashtag(LocalDateTime.now());
        MemberHashtag savedMemberHashtag = memberHashtagRepository.save(memberHashtag);

        MemberHashtag findMemberHashtag = memberHashtagRepository.findById(savedMemberHashtag.getUid()).get();

        Assertions.assertThat(findMemberHashtag.getAddTime()).isEqualTo(memberHashtag.getAddTime());
        Assertions.assertThat(findMemberHashtag).isEqualTo(memberHashtag);

        memberHashtagRepository.delete(memberHashtag);
        System.out.println("=========END TEST MEMBER HASHTAG=========");
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE MEMBER HASHTAG=========");

        MemberHashtag memberHashtag1 = new MemberHashtag(LocalDateTime.now());
        MemberHashtag memberHashtag2 = new MemberHashtag(LocalDateTime.now());
        memberHashtagRepository.save(memberHashtag1);
        memberHashtagRepository.save(memberHashtag2);

        System.out.println("=========END SAVE MEMBER HASHTAG=========");

        System.out.println("=========START FIND MEMBER HASHTAG=========");

        // 단건 조회 검증
        MemberHashtag findMemberHashtag1 = memberHashtagRepository.findById(memberHashtag1.getUid()).get();
        MemberHashtag findMemberHashtag2 = memberHashtagRepository.findById(memberHashtag2.getUid()).get();
        Assertions.assertThat(findMemberHashtag1).isEqualTo(memberHashtag1);
        Assertions.assertThat(findMemberHashtag2).isEqualTo(memberHashtag2);

        System.out.println("=========END FIND MEMBER HASHTAG=========");

        System.out.println("=========START FIND MEMBER HASHTAG LIST=========");
        //리스트 조회 검증
        List<MemberHashtag> all = memberHashtagRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND MEMBER HASHTAG LIST=========");

        System.out.println("=========START COUNT MEMBER HASHTAG=========");
        //카운트 검증
        long count = memberHashtagRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT MEMBER HASHTAG=========");

        System.out.println("=========START DELETE MEMBER HASHTAG=========");
        //삭제 검증
        memberHashtagRepository.delete(memberHashtag1);
        memberHashtagRepository.delete(memberHashtag2);
        long deletedCount = memberHashtagRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE MEMBER HASHTAG=========");
    }
}
