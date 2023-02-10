package nyongnyong.pangparty.repository.hashtag;

import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    @Query("select h from Hashtag h where h.name = ?1")
    Hashtag findHashtagByHashtagName(@Param(value = "name") String hashtagName);
//    Hashtag findHashtagByHashtagName(String hashtagName);

//    boolean isExistHashtagByHashtagName(String hashtagName);
}
