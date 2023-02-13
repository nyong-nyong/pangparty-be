package nyongnyong.pangparty.service.hashtag;

import nyongnyong.pangparty.dto.hashtag.HashtagSearchRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HashtagService {
    Hashtag addHashtagIfHashtagNameExists(String hashtagName);

    Page<HashtagSearchRes> searchHashtag(SearchReq conditions, Pageable pageable);
}
