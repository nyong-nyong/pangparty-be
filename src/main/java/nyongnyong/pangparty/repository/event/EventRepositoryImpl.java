package nyongnyong.pangparty.repository.event;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.event.EventIntroduceRes;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.QEvent;
import nyongnyong.pangparty.entity.event.QEventLike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid) {
        QEvent qEvent = QEvent.event;
        QEventLike eventLike = QEventLike.eventLike;

        Long tempIsLiked = queryFactory.select(qEvent.count())
                .from(qEvent)
                .leftJoin(eventLike)
                .on(eventLike.event.uid.eq(qEvent.uid))
                .where(qEvent.uid.eq(eventUid).and(eventLike.member.uid.eq(memberUid)))
                .fetchOne();

        Event event = queryFactory.selectFrom(qEvent)
                .where(qEvent.uid.eq(eventUid))
                .fetchOne();

        boolean isLiked = true;
        if(tempIsLiked == 0) {
            isLiked = false;
        }

        EventIntroduceRes eventIntroduceRes = new EventIntroduceRes(event.getEventTarget().getTargetMember().getMemberProfile().getId()
                , event.getDDay(), event.getEventName(), isLiked, event.getIntroduction(), event.getImgUrl(),
                event.getEventHashtags(), event.getAlbum().getAlbumMedia());


        return eventIntroduceRes;
    }
}
