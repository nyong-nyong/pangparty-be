package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.dto.event.EventIntroduceRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EventRepositoryCustom {
    EventIntroduceRes findEventIntroduceByEventUid(Long hostUid, Long eventUid);

    Page<Event> searchEvent(SearchReq conditions, Pageable pageable);
}
