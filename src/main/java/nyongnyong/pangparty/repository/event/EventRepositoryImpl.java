package nyongnyong.pangparty.repository.event;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.event.EventIntroduceRes;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.QEvent;
import nyongnyong.pangparty.entity.event.QEventHashtag;
import nyongnyong.pangparty.entity.event.QEventLike;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid) {
        QEvent qEvent = QEvent.event;
        QEventLike eventLike = QEventLike.eventLike;
        QEventHashtag eventHashtag = QEventHashtag.eventHashtag;

        Integer tempIsLiked = queryFactory.selectOne()      // select(qEvent.count()) 대신에 selectOne() 사용, count보다 성능이 빠름! 조건에 해당하는 row 1개만 찾으면 종료하기 때문 (exist라고 하는 듯)
                .from(qEvent)
                .leftJoin(eventLike)
                .on(eventLike.event.uid.eq(qEvent.uid))
                .where(qEvent.uid.eq(eventUid).and(eventLike.member.uid.eq(memberUid)))
                .fetchOne();

        Event event = queryFactory.selectFrom(qEvent)
                .leftJoin(qEvent.eventHashtags).fetchJoin()
                .leftJoin(qEvent.album).fetchJoin()
                .where(qEvent.uid.eq(eventUid))
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
//        List<String> albumMediaUrls = event.getAlbum().getAlbumMedia().stream().map(AlbumMedia::getMediaUrl).collect(Collectors.toList());

        boolean isLiked = true;
        if(tempIsLiked == null || tempIsLiked == 0){
            isLiked = false;
        }

        EventIntroduceRes eventIntroduceRes = new EventIntroduceRes(event.getEventTarget().getTargetMember().getMemberProfile().getId()
                , event.getDDay(), event.getEventName(), isLiked, event.getIntroduction(), event.getImgUrl(),
                hashtagNames);


        return eventIntroduceRes;
    }


}
