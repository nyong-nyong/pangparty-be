package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.dto.event.EventIntroduceRes;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.hashtag.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

//    @Query("select e, mp.id, true,  from Event e left join fetch e.album left join EventHashtag eht left join Hashtag ht left join EventLike el left join EventTarget et left join Member m left join MemberProfile mp left join AlbumMedia am where e.uid = ?1")
//    Event findEventIntroduceByUid(Long eventUid);

//    @Query("select e from Event e order by e.uid desc")
//    Page<Event> findEvents(Pageable pageable);

//    @Query("select new nyongnyong.pangparty.dto.event.EventIntroduceRes(mp.id, e.dDay, e.eventName, " +
//            "case el.member.uid when ?2 then true else false, " +
//            "e.introduction, e.imgUrl, eh, am) from Event e " +
//            "left join EventTarget et left join MemberProfile mp " +
//            "left join EventLike el left join EventHashtag eh left join Hashtag h " +
//            "join e.album.albumMedia am " +
//            "where e.uid = ?1 and et.event.uid = ?1 " +
//            "and et.targetMember.uid = mp.memberUid and el.member.uid = ?2" +
//            "and eh.event.uid = ?1 and eh.event.uid = ?1")
//    Event findEventIntroduceByEventUidAndMemberUid(Long eventUid, Long memberUid);
//
//    @Query("")
//    boolean findIsLikedByEventUidAndMemberUid(Long eventUid, Long memberUid);
//
//    @Query("select ht.name from Event e left join EventHashtag eht left join Hashtag ht where e.uid = ?1")
//    List<Hashtag> findHashtagsByEventUid(Long eventUid);
//    @Query("select am.mediaUrl from Event e left join fetch e.album a left join fetch a.albumMedia am where am.album.event.uid = ?1")
//    List<AlbumMedia> findAlbumMediasByEventUid(Long eventUid);

}
