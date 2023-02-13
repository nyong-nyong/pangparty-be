package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.event.*;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventLike;
import nyongnyong.pangparty.entity.event.EventTarget;
import nyongnyong.pangparty.entity.member.Friendship;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import nyongnyong.pangparty.repository.event.BannerRepository;
import nyongnyong.pangparty.repository.event.EventLikeRepository;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.event.EventTargetRepository;
import nyongnyong.pangparty.repository.member.FriendshipRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import nyongnyong.pangparty.service.auth.NotificationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventTargetRepository eventTargetRepository;
    private final EventLikeRepository eventLikeRepository;
    private final MemberRepository memberRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final BannerRepository bannerRepository;
    private final FriendshipRepository friendshipRepository;
    private final NotificationService notificationService;

    @Override
    public boolean isExistEventByEventUid(Long eventUid) {
        return eventRepository.existsById(eventUid);
    }

    @Override
    @Transactional
    public Event addEventAndEventTarget(Long hostUid, EventCreateReq eventCreateReq) {
        Event event = toEventEntity(hostUid, eventCreateReq);
        eventRepository.save(event);
        eventTargetRepository.save(toEventTargetEntity(eventCreateReq, event));

        // 주인공에게 알림 전송
        notificationService.alertTargetEvent(event.getUid(), hostUid, eventCreateReq.getTargetId());

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
