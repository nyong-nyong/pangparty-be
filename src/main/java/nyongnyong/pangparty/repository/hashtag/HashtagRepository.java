package nyongnyong.pangparty.repository.hashtag;

import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findHashtagByHashtagName(String hashtagName);
//    Hashtag findHashtagByHashtagName(String hashtagName);

//    boolean isExistHashtagByHashtagName(String hashtagName);
}
