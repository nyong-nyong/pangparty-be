package nyongnyong.pangparty.service.hashtag;

import nyongnyong.pangparty.entity.hashtag.Hashtag;

public interface HashtagService {
    Hashtag addHashtagIfHashtagNameExists(String hashtagName);
}
