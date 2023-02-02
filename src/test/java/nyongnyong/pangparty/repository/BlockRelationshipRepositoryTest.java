package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.member.BlockRelationship;
import nyongnyong.pangparty.repository.member.BlockRelationshipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class BlockRelationshipRepositoryTest {

    @Autowired
    BlockRelationshipRepository blockRelationshipRepository;

    @Test
    public void testBlockRelationship() {
        BlockRelationship blockRelationship = new BlockRelationship(LocalDateTime.now());
        BlockRelationship savedBlockRelationship = blockRelationshipRepository.save(blockRelationship);

        BlockRelationship findBlockRelationship = blockRelationshipRepository.findById(savedBlockRelationship.getUid()).get();

        Assertions.assertThat(findBlockRelationship.getBlockTime()).isEqualTo(blockRelationship.getBlockTime());
        Assertions.assertThat(findBlockRelationship).isEqualTo(blockRelationship);
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE BLOCKRELATIONSHIP=========");

        BlockRelationship blockRelationship1 = new BlockRelationship(LocalDateTime.now());
        BlockRelationship blockRelationship2 = new BlockRelationship(LocalDateTime.now());
        blockRelationshipRepository.save(blockRelationship1);
        blockRelationshipRepository.save(blockRelationship2);

        System.out.println("=========END SAVE BLOCKRELATIONSHIP=========");

        System.out.println("=========START FIND BLOCKRELATIONSHIP=========");

        // 단건 조회 검증
        BlockRelationship findBlockRelationship1 = blockRelationshipRepository.findById(blockRelationship1.getUid()).get();
        BlockRelationship findBlockRelationship2 = blockRelationshipRepository.findById(blockRelationship2.getUid()).get();
        Assertions.assertThat(findBlockRelationship1).isEqualTo(blockRelationship1);
        Assertions.assertThat(findBlockRelationship2).isEqualTo(blockRelationship2);

        System.out.println("=========END FIND BLOCKRELATIONSHIP=========");

        System.out.println("=========START FIND BLOCKRELATIONSHIP LIST=========");
        //리스트 조회 검증
        List<BlockRelationship> all = blockRelationshipRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND BLOCKRELATIONSHIP LIST=========");

        System.out.println("=========START COUNT BLOCKRELATIONSHIP=========");
        //카운트 검증
        long count = blockRelationshipRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT BLOCKRELATIONSHIP=========");

        System.out.println("=========START DELETE BLOCKRELATIONSHIP=========");
        //삭제 검증
        blockRelationshipRepository.delete(blockRelationship1);
        blockRelationshipRepository.delete(blockRelationship2);
        long deletedCount = blockRelationshipRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE BLOCKRELATIONSHIP=========");
    }
}
