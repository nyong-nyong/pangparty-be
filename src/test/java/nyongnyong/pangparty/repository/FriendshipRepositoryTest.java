package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.member.Friendship;
import nyongnyong.pangparty.repository.member.FriendshipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class FriendshipRepositoryTest {

    @Autowired
    FriendshipRepository friendshipRepository;

    @Test
    public void testFriendship() {
        Friendship friendship = new Friendship(LocalDateTime.now());
        Friendship savedFriendship = friendshipRepository.save(friendship);

        Friendship findFriendship = friendshipRepository.findById(savedFriendship.getUid()).get();

        Assertions.assertThat(findFriendship.getFollowTime()).isEqualTo(friendship.getFollowTime());
        Assertions.assertThat(findFriendship).isEqualTo(friendship);
    }

    @Test
    public void basicCRUD() {
        System.out.println("=========START SAVE FRIENDSHIP=========");

        Friendship friendship1 = new Friendship(LocalDateTime.now());
        Friendship friendship2 = new Friendship(LocalDateTime.now());
        friendshipRepository.save(friendship1);
        friendshipRepository.save(friendship2);

        System.out.println("=========END SAVE FRIENDSHIP=========");

        System.out.println("=========START FIND FRIENDSHIP=========");

        // 단건 조회 검증
        Friendship findFriendship1 = friendshipRepository.findById(friendship1.getUid()).get();
        Friendship findFriendship2 = friendshipRepository.findById(friendship2.getUid()).get();
        Assertions.assertThat(findFriendship1).isEqualTo(friendship1);
        Assertions.assertThat(findFriendship2).isEqualTo(friendship2);

        System.out.println("=========END FIND FRIENDSHIP=========");

        System.out.println("=========START FIND FRIENDSHIP LIST=========");
        //리스트 조회 검증
        List<Friendship> all = friendshipRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        System.out.println("=========END FIND FRIENDSHIP LIST=========");

        System.out.println("=========START COUNT FRIENDSHIP=========");
        //카운트 검증
        long count = friendshipRepository.count();
        Assertions.assertThat(count).isEqualTo(2);
        System.out.println("=========END COUNT FRIENDSHIP=========");

        System.out.println("=========START DELETE FRIENDSHIP=========");
        //삭제 검증
        friendshipRepository.delete(friendship1);
        friendshipRepository.delete(friendship2);
        long deletedCount = friendshipRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);
        System.out.println("=========END DELETE FRIENDSHIP=========");
    }
}
