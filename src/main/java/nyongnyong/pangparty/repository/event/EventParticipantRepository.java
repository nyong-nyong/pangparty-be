package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {

    @Query("SELECT ep FROM EventParticipant ep WHERE ep.event.uid = :eventUid AND ep.member.uid = :memberUid")
    EventParticipant findByMemberUidAndEventUid(Long memberUid, Long eventUid);

    void deleteByMemberUidAndEventUid(Long memberUid, Long eventUid);

}
