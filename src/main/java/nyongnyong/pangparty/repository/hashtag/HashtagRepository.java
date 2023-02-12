package nyongnyong.pangparty.repository.hashtag;

import nyongnyong.pangparty.dto.event.SimpleHashtagName;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    @Query("select h from Hashtag h where h.name = ?1")
    Hashtag findHashtagByHashtagName(@Param(value = "name") String hashtagName);

    Page<SimpleHashtagName> searchHashtag(SearchReq conditions, Pageable pageable);
//    Hashtag findHashtagByHashtagName(String hashtagName);

//    boolean isExistHashtagByHashtagName(String hashtagName);
}
