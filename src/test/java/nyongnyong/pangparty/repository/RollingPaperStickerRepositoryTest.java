package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperStickerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RollingPaperStickerRepositoryTest {

    @Autowired
    RollingPaperStickerRepository rollingPaperStickerRepository;

    @Test
    public void testRollingPaper() {

        RollingPaperSticker rollingPaperSticker = new RollingPaperSticker(now(), 100, 120, "100", 27.45f, 100.0f);
        RollingPaperSticker savedRollingPaperSticker = rollingPaperStickerRepository.save(rollingPaperSticker);

        RollingPaperSticker findRollingPaperSticker = rollingPaperStickerRepository.findById(savedRollingPaperSticker.getUid()).get();

        Assertions.assertThat(findRollingPaperSticker.getUid()).isEqualTo(rollingPaperSticker.getUid());
        Assertions.assertThat(findRollingPaperSticker.getCreateTime()).isEqualTo(rollingPaperSticker.getCreateTime());
        Assertions.assertThat(findRollingPaperSticker.getLeftLoc()).isEqualTo(rollingPaperSticker.getLeftLoc());
        Assertions.assertThat(findRollingPaperSticker.getTopLoc()).isEqualTo(rollingPaperSticker.getTopLoc());
        Assertions.assertThat(findRollingPaperSticker.getZIndex()).isEqualTo(rollingPaperSticker.getZIndex());
        Assertions.assertThat(findRollingPaperSticker.getAngle()).isEqualTo(rollingPaperSticker.getAngle());
        Assertions.assertThat(findRollingPaperSticker.getScale()).isEqualTo(rollingPaperSticker.getScale());
        Assertions.assertThat(findRollingPaperSticker).isEqualTo(rollingPaperSticker);
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE ROLLINGPAPER STICKER=========");
        RollingPaperSticker rollingPaperSticker1 = new RollingPaperSticker(now(), 100, 120, "100", 27.45f, 100.0f);
        RollingPaperSticker rollingPaperSticker2 = new RollingPaperSticker(now(), 50, 122, "1", 30.99f, 75f);
        rollingPaperStickerRepository.save(rollingPaperSticker1);
        rollingPaperStickerRepository.save(rollingPaperSticker2);
        System.out.println("=========END SAVE ROLLINGPAPER STICKER=========");

        System.out.println("=========START FIND ROLLINGPAPER STICKER=========");
        //단건 조회 검증
        RollingPaperSticker findRollingPaperSticker1 = rollingPaperStickerRepository.findById(rollingPaperSticker1.getUid()).get();
        RollingPaperSticker findRollingPaperSticker2 = rollingPaperStickerRepository.findById(rollingPaperSticker2.getUid()).get();
        assertThat(findRollingPaperSticker1).isEqualTo(rollingPaperSticker1);
        assertThat(findRollingPaperSticker2).isEqualTo(rollingPaperSticker2);
        System.out.println("=========START FIND ROLLINGPAPER STICKER=========");


        System.out.println("=========START FIND ROLLINGPAPER STICKER=========");
        //리스트 조회 검증
        List<RollingPaperSticker> all = rollingPaperStickerRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = rollingPaperStickerRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        rollingPaperStickerRepository.delete(rollingPaperSticker1);
        rollingPaperStickerRepository.delete(rollingPaperSticker2);
        long deletedCount = rollingPaperStickerRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void testTopLoc() {
        RollingPaperSticker rollingPaperSticker1 = new RollingPaperSticker(now(), 100, 120, "100", 27.45f, 100.0f);
        RollingPaperSticker rollingPaperSticker2 = new RollingPaperSticker(now(), 50, 122, "1", 30.99f, 75f);

        rollingPaperStickerRepository.save(rollingPaperSticker1);
        rollingPaperStickerRepository.save(rollingPaperSticker2);

        List<RollingPaperSticker> all = rollingPaperStickerRepository.findRollingPaperStickersByTopLoc(100, 121);
        assertThat(all.size()).isEqualTo(1);

    }
}
