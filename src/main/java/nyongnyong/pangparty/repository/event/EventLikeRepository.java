package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventLike;
import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.PreRemove;

public interface EventLikeRepository extends JpaRepository<EventLike, Long> {
    @Query("select el.uid from EventLike el where el.member.uid = ?1 and el.event.uid = ?2")
    Long findEventLikeUidByMemberUidAndEventUid(Long memberUid, Long eventUid);

    @PreRemove
    void deleteByEventUidAndMemberUid(Long eventUid, Long memberUid);

    void deleteByUid(Long eventLikeUid);

    EventLike findEventLikeByMemberAndEvent(Member member, Event event);
}
