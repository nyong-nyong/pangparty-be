package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.dto.event.EventIntroduceRes;

public interface EventRepositoryCustom {
    EventIntroduceRes findEventIntroduceByEventUid(Long hostUid, Long eventUid);
}
