package nyongnyong.pangparty.service.hashtag;

import nyongnyong.pangparty.dto.event.SimpleHashtagName;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HashtagService {
    Hashtag addHashtagIfHashtagNameExists(String hashtagName);

    Page<SimpleHashtagName> searchHashtag(SearchReq conditions, Pageable pageable);
}
