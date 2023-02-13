package nyongnyong.pangparty.service.hashtag;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.hashtag.HashtagSearchRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import nyongnyong.pangparty.repository.hashtag.HashtagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    @Override
    public Hashtag addHashtagIfHashtagNameExists(String hashtagName) {    // 이름의 해시태그 있는지 확인 후 없으면 넣는다.
        if (hashtagRepository.findHashtagByHashtagName(hashtagName) == null) {
            Hashtag hashtag = new Hashtag(hashtagName, LocalDateTime.now());
            hashtagRepository.save(hashtag);

            return hashtagRepository.findHashtagByHashtagName(hashtagName);
        } else {
            return hashtagRepository.findHashtagByHashtagName(hashtagName);
        }
    }

    @Override
    public Page<HashtagSearchRes> searchHashtag(SearchReq conditions, Pageable pageable) {
        return hashtagRepository.searchHashtag(conditions, pageable).map(HashtagSearchRes::new);
    }
}
