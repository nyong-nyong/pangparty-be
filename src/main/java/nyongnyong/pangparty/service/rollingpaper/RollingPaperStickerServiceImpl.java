package nyongnyong.pangparty.service.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerReq;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperStickerRes;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventParticipant;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperSticker;
import nyongnyong.pangparty.entity.rollingpaper.Sticker;
import nyongnyong.pangparty.exception.EventNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.RollingPaperNotFoundException;
import nyongnyong.pangparty.exception.StickerNotFoundException;
import nyongnyong.pangparty.repository.event.EventParticipantRepository;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperStickerRepository;
import nyongnyong.pangparty.repository.rollingpaper.StickerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RollingPaperStickerServiceImpl implements RollingPaperStickerService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final EventParticipantRepository eventParticipantRepository;

    private final StickerRepository stickerRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final RollingPaperStickerRepository rollingPaperStickerRepository;

    @Override
    @Transactional
    public List<RollingPaperStickerRes> findRollingPaperStickersByTopLoc(Long eventUid, Long rollingPaperUid, int topStart, int topEnd) {
        // check if the event exists
        Optional<Event> event = eventRepository.findById(eventUid);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }

        // check if rollingpaper exists
        if (event.get().getRollingPaper().getUid().equals(rollingPaperUid) || !rollingPaperRepository.existsById(rollingPaperUid)) {
            throw new RollingPaperNotFoundException();
        }

        return rollingPaperStickerRepository.findRollingPaperStickersByTopLoc(rollingPaperUid, topStart, topEnd).stream().map(RollingPaperStickerRes::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long addRollingPaperSticker(Long eventUid, @Valid final RollingPaperStickerReq rollingPaperStickerReq) {
        Optional<Event> event = eventRepository.findById(eventUid);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }

        Optional<RollingPaper> rollingPaper = rollingPaperRepository.findById(rollingPaperStickerReq.getRollingPaperUid());
        if (rollingPaper.isEmpty()) {
            throw new RollingPaperNotFoundException();
        }

        Optional<Sticker> sticker = stickerRepository.findById(rollingPaperStickerReq.getStickerUid());
        if (sticker.isEmpty()) {
            throw new StickerNotFoundException();
        }

        RollingPaperSticker rollingPaperSticker = toEntity(rollingPaper.get(), sticker.get(), rollingPaperStickerReq);
        rollingPaperStickerRepository.save(rollingPaperSticker);
        return rollingPaperSticker.getUid();
    }

    @Override
    @Transactional
    public Long addRollingPaperSticker(Long memberUid, Long eventUid, @Valid final RollingPaperStickerReq rollingPaperStickerReq) {
        Optional<Event> event = eventRepository.findById(eventUid);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }

        Optional<Member> member = memberRepository.findById(memberUid);
        if (member.isEmpty()) {
            throw new MemberNotFoundException();
        }

        Optional<RollingPaper> rollingPaper = rollingPaperRepository.findById(rollingPaperStickerReq.getRollingPaperUid());
        if (rollingPaper.isEmpty()) {
            throw new RollingPaperNotFoundException();
        }

        Optional<Sticker> sticker = stickerRepository.findById(rollingPaperStickerReq.getStickerUid());
        if (sticker.isEmpty()) {
            throw new StickerNotFoundException();
        }

        // check if the member is already participating in the event
        if (eventParticipantRepository.findByMemberUidAndEventUid(memberUid, eventUid) == null) {
            EventParticipant eventParticipant = toEventParticipantEntity(event.get(), member.get());
            eventParticipantRepository.save(eventParticipant);
        }

        RollingPaperSticker rollingPaperSticker = toEntity(member.get(), rollingPaper.get(), sticker.get(), rollingPaperStickerReq);
        rollingPaperStickerRepository.save(rollingPaperSticker);
        return rollingPaperSticker.getUid();
    }

    private RollingPaperSticker toEntity(RollingPaper rollingPaper, Sticker sticker, RollingPaperStickerReq rollingPaperStickerReq) {
        return RollingPaperSticker.builder()
                .sticker(sticker)
                .rollingPaper(rollingPaper)
                .topLoc(rollingPaperStickerReq.getTopLoc())
                .leftLoc(rollingPaperStickerReq.getLeftLoc())
                .zIndex(rollingPaperStickerReq.getZIndex())
                .scale(rollingPaperStickerReq.getScale()).build();
    }

    private RollingPaperSticker toEntity(Member member, RollingPaper rollingPaper, Sticker sticker, RollingPaperStickerReq rollingPaperStickerReq) {
        return RollingPaperSticker.builder()
                .sticker(sticker)
                .rollingPaper(rollingPaper)
                .member(member)
                .topLoc(rollingPaperStickerReq.getTopLoc())
                .leftLoc(rollingPaperStickerReq.getLeftLoc())
                .zIndex(rollingPaperStickerReq.getZIndex())
                .scale(rollingPaperStickerReq.getScale()).build();
    }

    EventParticipant toEventParticipantEntity(Event event, Member member) {
        return EventParticipant.builder().event(event).member(member).build();
    }

}
