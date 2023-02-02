package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}

