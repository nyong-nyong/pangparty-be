package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.dto.event.*;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventLike;
import nyongnyong.pangparty.entity.event.EventTarget;
import nyongnyong.pangparty.entity.member.Friendship;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperPiece;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;
import nyongnyong.pangparty.exception.EventNotFoundException;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.badge.MemberBadgeInfoRepository;
import nyongnyong.pangparty.repository.event.BannerRepository;
import nyongnyong.pangparty.repository.event.EventLikeRepository;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.event.EventTargetRepository;
import nyongnyong.pangparty.repository.member.FriendshipRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperPieceRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperStickerRepository;
import nyongnyong.pangparty.service.auth.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final AlbumMediaRepository albumMediaRepository;
    private final EventRepository eventRepository;
    private final EventTargetRepository eventTargetRepository;
    private final EventLikeRepository eventLikeRepository;
    private final MemberRepository memberRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final RollingPaperPieceRepository  rollingPaperPieceRepository;
    private final RollingPaperStickerRepository rollingPaperStickerRepository;
    private final BannerRepository bannerRepository;
    private final FriendshipRepository friendshipRepository;
    private final MemberBadgeInfoRepository memberBadgeInfoRepository;
    private final NotificationService notificationService;

    @Override
    public boolean isExistEventByEventUid(Long eventUid) {
        return eventRepository.existsById(eventUid);
    }

    @Override
    public boolean isEventTarget(Long memberUid, Long eventUid) {
        return eventRepository.findById(eventUid).get().getEventTarget().getUid().equals(memberUid);
    }

    @Override
    public boolean isEventHost(Long memberUid, Long eventUid) {
        return memberUid.equals(eventRepository.findById(eventUid).get().getHost().getUid());
    }

    @Override
    @Transactional
    public Event addEventAndEventTarget(Long hostUid, EventCreateReq eventCreateReq) {
        Event event = toEventEntity(hostUid, eventCreateReq);
        eventRepository.save(event);
        eventTargetRepository.save(toEventTargetEntity(eventCreateReq, event));

        // 주인공에게 알림 전송
        notificationService.alertTargetEvent(event.getUid(), hostUid, eventCreateReq.getTargetId());
        Member target = memberRepository.findMemberById(eventCreateReq.getTargetId());
        memberBadgeInfoRepository.updateReceiveCount(target.getUid());

        // 호스트의 팔로워들에게 알림 전송
        List<Friendship> friendships = friendshipRepository.findAllByFollowee(hostUid);
        for (Friendship friendship : friendships) {
            notificationService.alertFollowerEvent(event.getUid(), hostUid, friendship.getFollower().getUid());
        }
        return event;
    }

    @Override
    public Long addRollingPaper(Long eventUid) {
        RollingPaper rollingPaper = toRollingPaperEntity(eventRepository.findEventByUid(eventUid));
        rollingPaperRepository.save(rollingPaper);
        return rollingPaper.getUid();
    }

    @Override
    public Event getEventByEventUid(Long eventUid) {
        return eventRepository.findEventByUid(eventUid);
    }

    @Override
    public void likeEvent(Long memberUid, Long eventUid) {
        eventLikeRepository.save(toEventLikeEntity(memberUid, eventUid));
    }

    private EventLike toEventLikeEntity(Long memberUid, Long eventUid) {
        return EventLike.builder()
                .event(eventRepository.findEventByUid(eventUid))
                .member(memberRepository.findMemberByUid(memberUid))
                .likeTime(LocalDateTime.now()).build();
    }

    @Override
    @Transactional
    public void dislikeEvent(Long memberUid, Long eventUid) {
        log.info("memberUid = " + memberUid + " eventUid = " + eventUid);
//        Long eventLikeUid = eventLikeRepository.findEventLikeUidByMemberUidAndEventUid(memberUid, eventUid);
//        Event event = eventRepository.findEventByUid(eventUid);
//        Member member = memberRepository.findMemberByUid(memberUid);
//        System.out.println("Event: " + event + " Member: " + member + " EventLikeUid: " + eventLikeUid);
//        eventLikeRepository.delete(eventLikeRepository.findEventLikeByMemberAndEvent(member, event));
        eventLikeRepository.deleteByEventUidAndMemberUid(eventUid, memberUid);
//        eventLikeRepository.deleteByUid(eventLikeUid);
    }

    @Override
    public List<EventCard> findTodayStartEvents() {
        Pageable top3 = PageRequest.of(0, 3);
        return eventRepository.findTodayStartEvents(top3);
    }

    @Override
    public List<EventCard> findTodayEndEvents() {
        Pageable top3 = PageRequest.of(0, 3);
        return eventRepository.findTodayEndEvents(top3);
    }

    @Override
    public List<EventExportRes> findExportStatistics(Long eventUid) {
        return eventRepository.findExportStatistics(eventUid);
    }

    @Override
    public List<BannerRes> findBanners() {
        return bannerRepository.findBanners();
    }

    @Override
    public EventHeaderRes updateThumbnail(Long eventUid, String headerUrl) {
        eventRepository.updateThumbnail(eventUid, headerUrl);
        return new EventHeaderRes(eventUid, headerUrl);
    }

    @Override
    public Map<String, Object> findAllExport(Long eventUid) {

        Event event = eventRepository.findEventByUid(eventUid);
        if (event == null) {
            throw new EventNotFoundException();
        }

        List<EventExportRes> eventExportRes = eventRepository.findExportStatistics(eventUid);

        // event detail
        EventIntroduceRes eventIntroduceRes = eventRepository.findEventIntroduceByEventUid(eventUid);

        // rp list
        List<RollingPaperPiece> rollingPaperPieceList = rollingPaperPieceRepository.findAllByRollingPaperUid(event.getRollingPaper().getUid());

        // rp sticker list
        List<RollingPaperSticker> rollingPaperPieceStickerList = rollingPaperStickerRepository.findRollingPaperStickersByRollingPaperUid(event.getRollingPaper().getUid());

        //album 전체 조회
        List<AlbumMedia> albumMediaList = albumMediaRepository.findByAlbumUidOrderByUidAsc(event.getAlbum().getUid());
        List<AlbumMediaSimpleRes> albumMediaSimpleResList = albumMediaList.stream().map(AlbumMediaSimpleRes::new).collect(Collectors.toList());

        HashMap<String, Object> response = new HashMap<>();
        response.put("eventStatistics", eventExportRes);
        response.put("eventIntroduce", eventIntroduceRes);
        response.put("rollingPaperPieceList", rollingPaperPieceList);
        response.put("rollingPaperPieceStickerList", rollingPaperPieceStickerList);
        response.put("albumMediaList", albumMediaSimpleResList);

        return response;
    }

    @Override
    public Page<EventCard> searchEvent(SearchReq conditions, Pageable pageable) {
        return eventRepository.searchEvent(conditions, pageable).map(EventCard::new);
    }

    private Event toEventEntity(Long hostUid, EventCreateReq eventCreateReq){
        return Event.builder()
                .eventName(eventCreateReq.getEventName())
                .introduction(eventCreateReq.getIntroduction())
                .imgUrl(eventCreateReq.getImgUrl())
                .host(memberRepository.findMemberByUid(hostUid))
                .dDay(eventCreateReq.getDDay()).build();
    }

    private RollingPaper toRollingPaperEntity(Event event) {
        return RollingPaper.builder()
                .event(event).build();
    }

    private EventTarget toEventTargetEntity(EventCreateReq eventCreateReq, Event event){
        return EventTarget.builder()
                .targetMember(memberRepository.findMemberById(eventCreateReq.getTargetId()))
                .event(event)
                .addTime(LocalDateTime.now()).build();
    }

    @Override
    public List<EventCard> findReceivedEventsByMemberId(String memberId) {
        return eventRepository.findReceivedEventsByMemberId(memberId);
    }

    @Override
    public List<EventCard> findHostEventsByMemberId(String memberId) {
        return eventRepository.findHostEventsByMemberId(memberId);
    }

    @Override
    public List<EventCard> findInvolvingEventsByMemberId(String memberId) {
        return eventRepository.findInvolvingEventsByMemberId(memberId);
    }

    @Override
    public List<EventCard> findInvolvedEventsByMemberId(String memberId) {
        return eventRepository.findInvolvedEventsByMemberId(memberId);
    }

    @Override
    public EventIntroduceRes findEventIntroduceByEventUid(Long memberUid, Long eventUid) {
        return eventRepository.findEventIntroduceByEventUid(memberUid, eventUid);
    }

}
