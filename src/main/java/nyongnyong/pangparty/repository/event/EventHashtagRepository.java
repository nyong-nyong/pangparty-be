package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.EventHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventHashtagRepository extends JpaRepository<EventHashtag, Long> {
    @Query("select eh from EventHashtag eh where eh.event.uid = :eventUid and eh.hashtag.uid = :hashtagUid")
    EventHashtag findEventHashtagByEventUidAndHashtagUid(@Param(value = "eventUid") Long eventUid, @Param(value = "hashtagUid") Long hashtagUid);
}
