package nyongnyong.pangparty.repository.hashtag;

import nyongnyong.pangparty.dto.event.SimpleHashtagName;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HashtagRepositoryCustom {

    Page<Hashtag> searchHashtag(SearchReq conditions, Pageable pageable);
}
