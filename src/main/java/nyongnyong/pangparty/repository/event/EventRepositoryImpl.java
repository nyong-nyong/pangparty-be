package nyongnyong.pangparty.repository.event;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.event.EventIntroduceRes;
import nyongnyong.pangparty.dto.event.SimpleHashtagName;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.QEvent;
import nyongnyong.pangparty.entity.event.QEventLike;
import nyongnyong.pangparty.entity.rollingpaper.QRollingPaper;
import nyongnyong.pangparty.entity.rollingpaper.QRollingPaperPiece;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid) {
        QEvent qEvent = QEvent.event;
        QEventLike eventLike = QEventLike.eventLike;

        Integer tempIsLiked = queryFactory.selectOne()      // select(qEvent.count()) 대신에 selectOne() 사용, count보다 성능이 빠름! 조건에 해당하는 row 1개만 찾으면 종료하기 때문 (exist라고 하는 듯)
                .from(qEvent)
                .leftJoin(eventLike)
                .on(eventLike.event.uid.eq(qEvent.uid))
                .where(qEvent.uid.eq(eventUid).and(eventLike.member.uid.eq(memberUid)))
                .fetchOne();

        Long likeCnt = queryFactory.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.event.uid.eq(eventUid))
                .fetchOne();

        Event event = queryFactory.selectFrom(qEvent)
                .leftJoin(qEvent.eventHashtags).fetchJoin()
                .leftJoin(qEvent.album).fetchJoin()
                .where(qEvent.uid.eq(eventUid))
                .fetchOne();

        QRollingPaper qRollingPaper = QRollingPaper.rollingPaper;
        RollingPaper rollingPaper = queryFactory.select(qRollingPaper).from(qRollingPaper)
                .where(qRollingPaper.event.uid.eq(eventUid))
                .fetchOne();

        QRollingPaperPiece qRollingPaperPiece = QRollingPaperPiece.rollingPaperPiece;

        Long rollingPaperCnt = queryFactory.select(qRollingPaperPiece.count())
                .from(qRollingPaperPiece)
                .where(qRollingPaperPiece.rollingPaper.uid.eq(rollingPaper.getUid()))
                .fetchOne();

//        List<String> hashtagNames = queryFactory.select(qEvent.eventHashtags.any().hashtag.name)
//                .from(qEvent)
//                .where(qEvent.uid.eq(eventUid).and(qEvent.eventHashtags.any().event.uid.eq(eventUid)))
//                .fetch();
//
//        List<String> albumMediaUrls = queryFactory.select(qEvent.album.albumMedia.any().mediaUrl)
//                .from(qEvent)
//                .where(qEvent.uid.eq(eventUid))
//                .fetch();

//        System.out.println(albumMediaUrls);

        List<String> hashtagNames = event.getEventHashtags().stream().map(eventHashtag1 -> eventHashtag1.getHashtag().getName()).collect(Collectors.toList());  // 이거 데이터 없을때 validation 해야할듯?
        ArrayList<SimpleHashtagName> hashtags = new ArrayList<>();
        for (String hashtagName : hashtagNames) {
            SimpleHashtagName simpleHashtagName = new SimpleHashtagName(hashtagName);
            hashtags.add(simpleHashtagName);
        }
        log.debug("hashtags: {}", hashtags);
//        List<String> albumMediaUrls = event.getAlbum().getAlbumMedia().stream().map(AlbumMedia::getMediaUrl).collect(Collectors.toList());

        boolean isLiked = tempIsLiked != null && tempIsLiked != 0;

        return new EventIntroduceRes(event.getEventTarget().getTargetMember().getMemberProfile().getId()
                , event.getDDay(), event.getEventName(), isLiked, event.getIntroduction(), event.getImgUrl(),
                hashtags, likeCnt, rollingPaperCnt, rollingPaper.getUid());
    }

    @Override
    public EventIntroduceRes findEventIntroduceByEventUid(Long eventUid) {
        QEvent qEvent = QEvent.event;
        QEventLike eventLike = QEventLike.eventLike;

//        Integer tempIsLiked = queryFactory.selectOne()      // select(qEvent.count()) 대신에 selectOne() 사용, count보다 성능이 빠름! 조건에 해당하는 row 1개만 찾으면 종료하기 때문 (exist라고 하는 듯)
//                .from(qEvent)
//                .leftJoin(eventLike)
//                .on(eventLike.event.uid.eq(qEvent.uid))
//                .where(qEvent.uid.eq(eventUid).and(eventLike.member.uid.eq(memberUid)))
//                .fetchOne();

        Long likeCnt = queryFactory.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.event.uid.eq(eventUid))
                .fetchOne();

        Event event = queryFactory.selectFrom(qEvent)
                .leftJoin(qEvent.eventHashtags).fetchJoin()
                .leftJoin(qEvent.album).fetchJoin()
                .where(qEvent.uid.eq(eventUid))
                .fetchOne();

        QRollingPaper qRollingPaper = QRollingPaper.rollingPaper;
        RollingPaper rollingPaper = queryFactory.select(qRollingPaper).from(qRollingPaper)
                .where(qRollingPaper.event.uid.eq(eventUid))
                .fetchOne();

        QRollingPaperPiece qRollingPaperPiece = QRollingPaperPiece.rollingPaperPiece;

        Long rollingPaperCnt = queryFactory.select(qRollingPaperPiece.count())
                .from(qRollingPaperPiece)
                .where(qRollingPaperPiece.rollingPaper.uid.eq(rollingPaper.getUid()))
                .fetchOne();

//        List<String> hashtagNames = queryFactory.select(qEvent.eventHashtags.any().hashtag.name)
//                .from(qEvent)
//                .where(qEvent.uid.eq(eventUid).and(qEvent.eventHashtags.any().event.uid.eq(eventUid)))
//                .fetch();
//
//        List<String> albumMediaUrls = queryFactory.select(qEvent.album.albumMedia.any().mediaUrl)
//                .from(qEvent)
//                .where(qEvent.uid.eq(eventUid))
//                .fetch();

//        System.out.println(albumMediaUrls);

        List<String> hashtagNames = event.getEventHashtags().stream().map(eventHashtag1 -> eventHashtag1.getHashtag().getName()).collect(Collectors.toList());  // 이거 데이터 없을때 validation 해야할듯?
        ArrayList<SimpleHashtagName> hashtags = new ArrayList<>();
        for (String hashtagName : hashtagNames) {
            SimpleHashtagName simpleHashtagName = new SimpleHashtagName(hashtagName);
            hashtags.add(simpleHashtagName);
        }
        log.debug("hashtags: {}", hashtags);
//        List<String> albumMediaUrls = event.getAlbum().getAlbumMedia().stream().map(AlbumMedia::getMediaUrl).collect(Collectors.toList());

        boolean isLiked = false;

        return new EventIntroduceRes(event.getEventTarget().getTargetMember().getMemberProfile().getId()
                , event.getDDay(), event.getEventName(), isLiked, event.getIntroduction(), event.getImgUrl(),
                hashtags, likeCnt, rollingPaperCnt, rollingPaper.getUid());
    }


    @Override
    public Page<Event> searchEvent(SearchReq conditions, Pageable pageable) {

        QEvent event = QEvent.event;

        List<Event> events
                = queryFactory
                .selectFrom(event)
                .where(event.eventName.contains(conditions.getKeyword())
                        , eqHashtagName(conditions.getHashtagName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(events, pageable, events.size());
    }

    private BooleanExpression eqHashtagName(String hashtagName) {
        return hashtagName != null ? QEvent.event.eventHashtags.any().hashtag.name.eq(hashtagName) : null;
    }

}
