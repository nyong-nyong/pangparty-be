package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.album.AlbumMediaLike;
import nyongnyong.pangparty.repository.album.AlbumMediaLikeRepository;
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
public class AlbumMediaLikeRepositoryTest {

    @Autowired
    AlbumMediaLikeRepository albumMediaLikeRepository;

    @Test
    @Transactional
    public void testAlbumMediaLike(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

        AlbumMediaLike albumMediaLike = new AlbumMediaLike(LocalDateTime.parse("2023-01-18 10:24:00", formatter));
        AlbumMediaLike savedAlbumMediaLike = albumMediaLikeRepository.save(albumMediaLike);

        AlbumMediaLike findAlbumMediaLike = albumMediaLikeRepository.findById(savedAlbumMediaLike.getUid()).get();

        Assertions.assertThat(findAlbumMediaLike.getLikeTime()).isEqualTo(albumMediaLike.getLikeTime());
    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        AlbumMediaLike albumMediaLike1 = new AlbumMediaLike(LocalDateTime.parse("2023-01-19 10:24:00", formatter));
        AlbumMediaLike albumMediaLike2 = new AlbumMediaLike(LocalDateTime.parse("2023-01-20 10:24:00", formatter));
        albumMediaLikeRepository.save(albumMediaLike1);
        albumMediaLikeRepository.save(albumMediaLike2);

        // 단건 조회 검증
        AlbumMediaLike findAlbumMediaLike1 = albumMediaLikeRepository.findById(albumMediaLike1.getUid()).get();
        AlbumMediaLike findAlbumMediaLike2 = albumMediaLikeRepository.findById(albumMediaLike2.getUid()).get();
        assertThat(findAlbumMediaLike1).isEqualTo(albumMediaLike1);
        assertThat(findAlbumMediaLike2).isEqualTo(albumMediaLike2);

        // 리스트 조회 검증
        List<AlbumMediaLike> all = albumMediaLikeRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = albumMediaLikeRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        albumMediaLikeRepository.delete(albumMediaLike1);
        albumMediaLikeRepository.delete(albumMediaLike2);

        long deletedCount = albumMediaLikeRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
