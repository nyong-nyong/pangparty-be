package nyongnyong.pangparty.repository.hashtag;

import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findHashtagByHashtagName(String hashtagName);

    boolean isExistHashtagByHashtagName(String hashtagName);
}
