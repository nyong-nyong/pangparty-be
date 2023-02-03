package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.album.AlbumMediaComment;
import nyongnyong.pangparty.entity.event.Banner;
import nyongnyong.pangparty.repository.album.AlbumMediaCommentRepository;
import nyongnyong.pangparty.repository.event.BannerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class AlbumMediaCommentRepositoryTest {

    @Autowired
    AlbumMediaCommentRepository albumMediaCommentRepository;

    @Test
    @Transactional
    public void testAlbumMediaComment(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

        AlbumMediaComment albumMediaComment = new AlbumMediaComment("댓글~ 입니다~ 댓그링", LocalDateTime.parse("2023-01-13 10:24:00", formatter), LocalDateTime.parse("2023-01-16 10:24:00", formatter));
        AlbumMediaComment savedAlbumMediaComment = albumMediaCommentRepository.save(albumMediaComment);

        AlbumMediaComment findAlbumMediaComment = albumMediaCommentRepository.findById(savedAlbumMediaComment.getUid()).get();

        Assertions.assertThat(findAlbumMediaComment.getContent()).isEqualTo(albumMediaComment.getContent());
        Assertions.assertThat(findAlbumMediaComment.getCreateTime()).isEqualTo(albumMediaComment.getCreateTime());
        Assertions.assertThat(findAlbumMediaComment.getModifyTime()).isEqualTo(albumMediaComment.getModifyTime());
    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        AlbumMediaComment albumMediaComment1 = new AlbumMediaComment("zzzzㅋㅋㅋㅋㅋ 귀여웡~", LocalDateTime.parse("2023-01-15 10:24:00", formatter), LocalDateTime.parse("2023-01-18 10:24:00", formatter));

        AlbumMediaComment albumMediaComment2 = new AlbumMediaComment("히히ㅣㅎ 댓글이다용", LocalDateTime.parse("2023-01-17 10:24:00", formatter), LocalDateTime.parse("2023-01-20 10:24:00", formatter));

        albumMediaCommentRepository.save(albumMediaComment1);
        albumMediaCommentRepository.save(albumMediaComment2);

        // 단건 조회 검증
        AlbumMediaComment findAlbumMediaComment1 = albumMediaCommentRepository.findById(albumMediaComment1.getUid()).get();
        AlbumMediaComment findAlbumMediaComment2 = albumMediaCommentRepository.findById(albumMediaComment2.getUid()).get();
        assertThat(findAlbumMediaComment1).isEqualTo(albumMediaComment1);
        assertThat(findAlbumMediaComment2).isEqualTo(albumMediaComment2);

        // 리스트 조회 검증
        List<AlbumMediaComment> all = albumMediaCommentRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = albumMediaCommentRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        albumMediaCommentRepository.delete(albumMediaComment1);
        albumMediaCommentRepository.delete(albumMediaComment2);

        long deletedCount = albumMediaCommentRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
