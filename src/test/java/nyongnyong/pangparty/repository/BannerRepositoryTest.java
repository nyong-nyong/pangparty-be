package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.event.Banner;
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
public class BannerRepositoryTest {

    @Autowired
    BannerRepository bannerRepository;

    @Test
    @Transactional
    public void testBanner(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // String to LocalDateTime: LocalDateTime.parse("2023-01-19 10:24:00", formatter);

        Banner banner = new Banner("영비나 생일추카해~~~ 사 랑 해 너 만 을", LocalDateTime.parse("2023-01-18 10:24:00", formatter), LocalDateTime.parse("2023-01-20 10:24:00", formatter), LocalDateTime.parse("2023-02-18 10:24:00", formatter));
        Banner savedBanner = bannerRepository.save(banner);

        Banner findBanner = bannerRepository.findById(savedBanner.getUid()).get();

        Assertions.assertThat(findBanner.getContent()).isEqualTo(banner.getContent());
        Assertions.assertThat(findBanner.getCreateTime()).isEqualTo(banner.getCreateTime());
        Assertions.assertThat(findBanner.getStartTime()).isEqualTo(banner.getStartTime());
        Assertions.assertThat(findBanner.getEndTime()).isEqualTo(banner.getEndTime());
    }

    @Test
    @Transactional
    public void basicCRUD(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Banner banner1 = new Banner("11영비나 생일추카해~~~ 사 랑 해 너 만 을", LocalDateTime.parse("2023-01-18 10:24:00", formatter), LocalDateTime.parse("2023-01-20 10:24:00", formatter), LocalDateTime.parse("2023-02-18 10:24:00", formatter));

        Banner banner2 = new Banner("222영비나 생일추카해~~~ 사 랑 해 너 만 을", LocalDateTime.parse("2023-01-18 10:24:00", formatter), LocalDateTime.parse("2023-01-20 10:24:00", formatter), LocalDateTime.parse("2023-02-18 10:24:00", formatter));

        bannerRepository.save(banner1);
        bannerRepository.save(banner2);

        // 단건 조회 검증
        Banner findBanner1 = bannerRepository.findById(banner1.getUid()).get();
        Banner findBanner2 = bannerRepository.findById(banner2.getUid()).get();
        assertThat(findBanner1).isEqualTo(banner1);
        assertThat(findBanner2).isEqualTo(banner2);

        // 리스트 조회 검증
        List<Banner> all = bannerRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = bannerRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        bannerRepository.delete(banner1);
        bannerRepository.delete(banner2);

        long deletedCount = bannerRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
