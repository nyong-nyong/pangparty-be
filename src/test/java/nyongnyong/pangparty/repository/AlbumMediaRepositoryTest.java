package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
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
public class AlbumMediaRepositoryTest {

    @Autowired
    AlbumMediaRepository albumMediaRepository;

    @Test
    @Transactional
    public void testAlbumMedia(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

        AlbumMedia albumMedia = new AlbumMedia("https://cdn.eyesmag.com/content/uploads/posts/2022/08/08/main-ad65ae47-5a50-456d-a41f-528b63071b7b.jpg",
                "jpg", LocalDateTime.parse("2023-01-20 10:24:00", formatter), LocalDateTime.parse("2023-01-18 10:24:00", formatter),
                "37.532600", "127.024612");
        AlbumMedia savedAlbumMedia = albumMediaRepository.save(albumMedia);

        AlbumMedia findAlbumMedia = albumMediaRepository.findById(savedAlbumMedia.getUid()).get();

        Assertions.assertThat(findAlbumMedia.getMediaUrl()).isEqualTo(albumMedia.getMediaUrl());
        Assertions.assertThat(findAlbumMedia.getExtension()).isEqualTo(albumMedia.getExtension());
        Assertions.assertThat(findAlbumMedia.getUploadTime()).isEqualTo(albumMedia.getUploadTime());
        Assertions.assertThat(findAlbumMedia.getTakenTime()).isEqualTo(albumMedia.getTakenTime());
        Assertions.assertThat(findAlbumMedia.getTakenLat()).isEqualTo(albumMedia.getTakenLat());
        Assertions.assertThat(findAlbumMedia.getTakenLng()).isEqualTo(albumMedia.getTakenLng());

    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        AlbumMedia albumMedia1 = new AlbumMedia("https://cdn.eyesmag.com/content/uploads/posts/2022/08/08/main-ad65ae47-5a50-456d-a41f-528b63071b7b.jpg",
                "jpg", LocalDateTime.parse("2023-01-17 10:24:00", formatter), LocalDateTime.parse("2023-01-13 10:24:00", formatter),
                "37.532600", "127.024612");

        AlbumMedia albumMedia2 = new AlbumMedia("https://cdn.imweb.me/upload/S201807025b39d1981b0b0/16b98d3e3d30e.jpg",
                "jpg", LocalDateTime.parse("2023-01-21 10:24:00", formatter), LocalDateTime.parse("2023-01-20 10:24:00", formatter),
                "37.532600", "127.024612");

        albumMediaRepository.save(albumMedia1);
        albumMediaRepository.save(albumMedia2);

        // 단건 조회 검증
        AlbumMedia findAlbumMedia1 = albumMediaRepository.findById(albumMedia1.getUid()).get();
        AlbumMedia findAlbumMedia2 = albumMediaRepository.findById(albumMedia2.getUid()).get();
        assertThat(findAlbumMedia1).isEqualTo(albumMedia1);
        assertThat(findAlbumMedia2).isEqualTo(albumMedia2);

        // 리스트 조회 검증
        List<AlbumMedia> all = albumMediaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = albumMediaRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        albumMediaRepository.delete(albumMedia1);
        albumMediaRepository.delete(albumMedia2);

        long deletedCount = albumMediaRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
