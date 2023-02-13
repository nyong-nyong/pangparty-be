package nyongnyong.pangparty.repository.hashtag;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static nyongnyong.pangparty.entity.hashtag.QHashtag.hashtag;

@Slf4j
@Repository
@RequiredArgsConstructor
public class HashtagRepositoryImpl implements HashtagRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Hashtag> searchHashtag(SearchReq conditions, Pageable pageable) {


        List<Hashtag> hashtags
                = queryFactory
                .selectFrom(hashtag)
                .where(hashtag.name.contains(conditions.getKeyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(hashtags, pageable, hashtags.size());
    }

}
