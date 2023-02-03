package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RollingPaperRepositoryTest {

    @Autowired
    RollingPaperRepository rollingPaperRepository;

    @Test
    public void testRollingPaper() {

        RollingPaper rollingPaper = new RollingPaper("orange");
        RollingPaper savedRollingPaper = rollingPaperRepository.save(rollingPaper);

        RollingPaper findRollingPaper = rollingPaperRepository.findById(savedRollingPaper.getUid()).get();

        Assertions.assertThat(findRollingPaper.getUid()).isEqualTo(rollingPaper.getUid());
        Assertions.assertThat(findRollingPaper.getBgColor()).isEqualTo(rollingPaper.getBgColor());
        Assertions.assertThat(findRollingPaper).isEqualTo(rollingPaper);
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE ROLLINGPAPER=========");
        RollingPaper rollingPaper1 = new RollingPaper("rollingPaper1");
        RollingPaper rollingPaper2 = new RollingPaper("rollingPaper");
        rollingPaperRepository.save(rollingPaper1);
        rollingPaperRepository.save(rollingPaper2);
        System.out.println("=========END SAVE ROLLINGPAPER=========");

        System.out.println("=========START FIND ROLLINGPAPER=========");
        //단건 조회 검증
        RollingPaper findMember1 = rollingPaperRepository.findById(rollingPaper1.getUid()).get();
        RollingPaper findMember2 = rollingPaperRepository.findById(rollingPaper2.getUid()).get();
        assertThat(findMember1).isEqualTo(rollingPaper1);
        assertThat(findMember2).isEqualTo(rollingPaper2);
        System.out.println("=========START FIND ROLLINGPAPER=========");


        System.out.println("=========START FIND ROLLINGPAPER=========");
        //리스트 조회 검증
        List<RollingPaper> all = rollingPaperRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = rollingPaperRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        rollingPaperRepository.delete(rollingPaper1);
        rollingPaperRepository.delete(rollingPaper2);
        long deletedCount = rollingPaperRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
