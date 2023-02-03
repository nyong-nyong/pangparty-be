package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.rollingpaper.RollingPaperPiece;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperPieceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RollingPaperPieceRepositoryTest {

    @Autowired
    RollingPaperPieceRepository rollingPaperPieceRepository;

    @Test
    public void testRollingPaper() {

        RollingPaperPiece rollingPaperPiece = new RollingPaperPiece("orange", LocalDateTime.now(), LocalDateTime.now(), "orange", "orange", "orange", "orange", "orange", "orange", "orange");
        RollingPaperPiece savedRollingPaperPiece = rollingPaperPieceRepository.save(rollingPaperPiece);

        RollingPaperPiece findRollingPaperPiece = rollingPaperPieceRepository.findById(savedRollingPaperPiece.getUid()).get();

        assertThat(findRollingPaperPiece.getUid()).isEqualTo(rollingPaperPiece.getUid());
        assertThat(findRollingPaperPiece.getWriterName()).isEqualTo(rollingPaperPiece.getWriterName());
        assertThat(findRollingPaperPiece.getCreateTime()).isEqualTo(rollingPaperPiece.getCreateTime());
        assertThat(findRollingPaperPiece.getModifyTime()).isEqualTo(rollingPaperPiece.getModifyTime());
        assertThat(findRollingPaperPiece.getContent()).isEqualTo(rollingPaperPiece.getContent());
        assertThat(findRollingPaperPiece.getBgColor()).isEqualTo(rollingPaperPiece.getBgColor());
        assertThat(findRollingPaperPiece.getBgImgUrl()).isEqualTo(rollingPaperPiece.getBgImgUrl());
        assertThat(findRollingPaperPiece.getBgImgAlt()).isEqualTo(rollingPaperPiece.getBgImgAlt());
        assertThat(findRollingPaperPiece.getFontFamily()).isEqualTo(rollingPaperPiece.getFontFamily());
        assertThat(findRollingPaperPiece.getTextColor()).isEqualTo(rollingPaperPiece.getTextColor());
        assertThat(findRollingPaperPiece.getTextAlign()).isEqualTo(rollingPaperPiece.getTextAlign());
        assertThat(findRollingPaperPiece).isEqualTo(rollingPaperPiece);
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE ROLLINGPAPER PIECE=========");
        RollingPaperPiece rollingPaperPiece1 = new RollingPaperPiece("rollingPaperPiece1", now(), now(), "rp1", "rp1", "rp1", "rp1", "rp1", "rp1", "rp1");
        RollingPaperPiece rollingPaperPiece2 = new RollingPaperPiece("rollingPaperPiece2", now(), now(), "rp2", "rp2", "rp2", "rp2", "rp2", "rp2", "rp2");
        rollingPaperPieceRepository.save(rollingPaperPiece1);
        rollingPaperPieceRepository.save(rollingPaperPiece2);
        System.out.println("=========END SAVE ROLLINGPAPER PIECE=========");

        System.out.println("=========START FIND ROLLINGPAPER PIECE=========");
        //단건 조회 검증
        RollingPaperPiece findMember1 = rollingPaperPieceRepository.findById(rollingPaperPiece1.getUid()).get();
        RollingPaperPiece findMember2 = rollingPaperPieceRepository.findById(rollingPaperPiece2.getUid()).get();
        assertThat(findMember1).isEqualTo(rollingPaperPiece1);
        assertThat(findMember2).isEqualTo(rollingPaperPiece2);
        System.out.println("=========START FIND ROLLINGPAPER PIECE=========");


        System.out.println("=========START FIND ROLLINGPAPER PIECE=========");
        //리스트 조회 검증
        List<RollingPaperPiece> all = rollingPaperPieceRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = rollingPaperPieceRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        rollingPaperPieceRepository.delete(rollingPaperPiece1);
        rollingPaperPieceRepository.delete(rollingPaperPiece2);
        long deletedCount = rollingPaperPieceRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
