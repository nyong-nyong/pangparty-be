package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.rollingpaper.Sticker;
import nyongnyong.pangparty.repository.rollingpaper.StickerRepository;
import nyongnyong.pangparty.service.rollingpaper.StickerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class StickerRepositoryTest {

    @Autowired
    StickerRepository stickerRepository;

    @Test
    public void testRollingPaper() {
        Sticker sticker = new Sticker("", "");
        Sticker savedSticker = stickerRepository.save(sticker);

        Sticker findSticker = stickerRepository.findById(savedSticker.getUid()).get();

        Assertions.assertThat(findSticker.getUid()).isEqualTo(sticker.getUid());
        Assertions.assertThat(findSticker.getStickerUrl()).isEqualTo(sticker.getStickerUrl());
        Assertions.assertThat(findSticker.getMetaTag()).isEqualTo(sticker.getMetaTag());
        Assertions.assertThat(findSticker).isEqualTo(sticker);
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE STICKER=========");
        Sticker sticker1 = new Sticker("naver", "50");
        Sticker sticker2 = new Sticker("yahoo", "100");
        stickerRepository.save(sticker1);
        stickerRepository.save(sticker2);
        System.out.println("=========END SAVE STICKER=========");

        System.out.println("=========START FIND STICKER=========");
        //단건 조회 검증
        Sticker findSticker1 = stickerRepository.findById(sticker1.getUid()).get();
        Sticker findSticker2 = stickerRepository.findById(sticker2.getUid()).get();
        assertThat(findSticker1).isEqualTo(sticker1);
        assertThat(findSticker2).isEqualTo(sticker2);
        System.out.println("=========START FIND STICKER=========");


        System.out.println("=========START FIND STICKER=========");
        //리스트 조회 검증
        List<Sticker> all = stickerRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = stickerRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        stickerRepository.delete(sticker1);
        stickerRepository.delete(sticker2);
        long deletedCount = stickerRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void paging() {
        System.out.println("=========START GET STICKER BY PAGE=========");

        // when
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Sticker> stickerPages = stickerRepository.findPage(pageRequest);

        // then
        List<Sticker> content = stickerPages.getContent();
        long totalElements = stickerPages.getTotalElements();

        assertThat(content.size()).isEqualTo(5);
        assertThat(totalElements).isEqualTo(5);
        System.out.println("content " + content);
        System.out.println("totalElements = " + totalElements);

        System.out.println("=========END GET STICKER BY PAGE=========");
    }
}
