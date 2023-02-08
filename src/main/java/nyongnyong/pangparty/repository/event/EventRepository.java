package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

    @Query("select new nyongnyong.pangparty.dto.event.EventCard(e.uid, e.eventName, mp.id, e.imgUrl, e.dDay)" +
            " from Event e" +
            " left join e.eventTarget.targetMember.memberProfile mp where mp.id = ?1")
    List<EventCard> findReceivedEventsByMemberId(String memberId);

}
