package nyongnyong.pangparty.service.event;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.dto.event.EventCreateReq;
import nyongnyong.pangparty.dto.event.EventExportRes;
import nyongnyong.pangparty.dto.event.EventIntroduceRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.dto.event.*;
import nyongnyong.pangparty.entity.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EventService {

    boolean isExistEventByEventUid(Long eventUid);

    boolean isEventTarget(Long memberUid, Long eventUid);

    boolean isEventHost(Long memberUid, Long eventUid);

    Event addEventAndEventTarget(Long hostUid, EventCreateReq eventCreateReq);
    List<EventCard> findReceivedEventsByMemberId(String memberId);

    List<EventCard> findHostEventsByMemberId(String memberId);

    List<EventCard> findInvolvingEventsByMemberId(String memberId);

    List<EventCard> findInvolvedEventsByMemberId(String memberId);

    EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid);

    Long addRollingPaper(Long eventUid);
    Event getEventByEventUid(Long eventUid);

    void likeEvent(Long memberUid, Long eventUid);

    void dislikeEvent(Long memberUid, Long eventUid);

    List<EventCard> findTodayStartEvents();

    List<EventCard> findTodayEndEvents();
    List<EventCard> findTrendingEvents();

    List<EventExportRes> findExportStatistics(Long eventUid);

    Page<EventCard> searchEvent(SearchReq conditions, Pageable pageable);

    List<BannerRes> findBanners();

    EventHeaderRes updateThumbnail(Long eventUid, String headerUrl);

    Map<String, Object> findAllExport(Long eventUid);
}
