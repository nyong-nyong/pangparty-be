package nyongnyong.pangparty.repository.hashtag;

import nyongnyong.pangparty.dto.event.SimpleHashtagName;
import nyongnyong.pangparty.dto.search.SearchReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class HashtagRepositoryImpl implements HashtagRepositoryCustom {

    @Override
    public Page<SimpleHashtagName> searchHashtag(SearchReq conditions, Pageable pageable) {
        return null;
    }

}
