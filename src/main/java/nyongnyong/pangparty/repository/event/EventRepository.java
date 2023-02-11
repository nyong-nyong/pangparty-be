package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

//    @Query("select new nyongnyong.pangparty.dto.event.EventIntroduceRes(mp.id, e.dDay, e.eventName," +
//            " case (select count(*) from e left join EventLike el where el.member.uid = ?1) when 0 then true else false end, e.introduction," +
//            " e.imgUrl, e.eventHashtags, e.album.albumMedia) from Event e" +
//            " left join e.eventTarget.targetMember.memberProfile mp" +
//            " left join EventLike el" +
//            " left join fetch e.eventHashtags left join fetch e.album where e.uid = ?2")
//    EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid);

    @Query("select new nyongnyong.pangparty.dto.event.EventCard(e.uid, e.eventName, mp.id, e.imgUrl, e.dDay)" +
            " from Event e" +
            " left join e.eventTarget.targetMember.memberProfile mp where mp.id = ?1")
    List<EventCard> findReceivedEventsByMemberId(String memberId);

    @Query("select new nyongnyong.pangparty.dto.event.EventCard(e.uid, e.eventName, mp.id, e.imgUrl, e.dDay)" +
            " from Event e" +
            " left join e.eventTarget.targetMember.memberProfile mp" +
            " left join e.host.memberProfile hmp where hmp.id = ?1")
    List<EventCard> findHostEventsByMemberId(String memberId);

    @Query("select new nyongnyong.pangparty.dto.event.EventCard(e.uid, e.eventName, mp.id, e.imgUrl, e.dDay)" +
            " from Event e" +
            " left join e.eventTarget.targetMember.memberProfile mp" +
            " left join e.eventParticipants p " +
            " where p.member.memberProfile.id = ?1 and e.dDay > SYSDATE()")
    List<EventCard> findInvolvingEventsByMemberId(String memberId);

    @Query("select new nyongnyong.pangparty.dto.event.EventCard(e.uid, e.eventName, mp.id, e.imgUrl, e.dDay)" +
            " from Event e" +
            " left join e.eventTarget.targetMember.memberProfile mp" +
            " left join e.eventParticipants p " +
            " where p.member.memberProfile.id = ?1 and e.dDay <= SYSDATE()")
    List<EventCard> findInvolvedEventsByMemberId(String memberId);

    @Query("select e from Event e where e.uid = ?1")
    Event findEventByUid(Long eventUid);

    @Query("select new nyongnyong.pangparty.dto.event.EventCard(e.uid, e.eventName, mp.id, e.imgUrl, e.dDay)" +
            " from Event e" +
            " left join e.eventTarget.targetMember.memberProfile mp" +
            " where e.dDay = CURRENT_DATE")
    List<EventCard> findTodayEndEvents();

    @Query("select new nyongnyong.pangparty.dto.event.EventCard(e.uid, e.eventName, mp.id, e.imgUrl, e.dDay)" +
            " from Event e" +
            " left join e.eventTarget.targetMember.memberProfile mp" +
            " where date(e.createTime) = CURRENT_DATE")
    List<EventCard> findTodayStartEvents();
}
