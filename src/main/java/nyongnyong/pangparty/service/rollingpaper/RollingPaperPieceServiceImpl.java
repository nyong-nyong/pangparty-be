package nyongnyong.pangparty.service.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperPieceReq;
import nyongnyong.pangparty.dto.rollingpaper.RollingPaperPieceRes;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventParticipant;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaperPiece;
import nyongnyong.pangparty.exception.EventNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.RollingPaperNotFoundException;
import nyongnyong.pangparty.repository.badge.MemberBadgeInfoRepository;
import nyongnyong.pangparty.repository.event.EventParticipantRepository;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperPieceRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RollingPaperPieceServiceImpl implements RollingPaperPieceService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final MemberBadgeInfoRepository memberBadgeInfoRepository;
    private final EventParticipantRepository eventParticipantRepository;
    private final RollingPaperRepository rollingPaperRepository;
    private final RollingPaperPieceRepository rollingPaperPieceRepository;

    @Override
    @Transactional
    public Page<RollingPaperPieceRes> findRollingPaperPieces(Long eventUid, Long rollingPaperUid, Pageable pageable) {
        // check if the event exists
        Optional<Event> event = eventRepository.findById(eventUid);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }

        // check if rollingpaper exists
        if (!event.get().getRollingPaper().getUid().equals(rollingPaperUid) || !rollingPaperRepository.existsById(rollingPaperUid)) {
            throw new RollingPaperNotFoundException();
        }

        return rollingPaperPieceRepository.findAllByRollingPaperUid(rollingPaperUid, pageable).map(RollingPaperPieceRes::new);
    }

    @Override
    @Transactional
    public Long addRollingPaperPiece(Long memberUid, Long eventUid, @Valid final RollingPaperPieceReq rollingPaperPieceReq) {
        Optional<Event> event = eventRepository.findById(eventUid);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }

        Optional<Member> member = memberRepository.findById(memberUid);
        if (member.isEmpty()) {
            throw new MemberNotFoundException();
        }

        Optional<RollingPaper> rollingPaper = rollingPaperRepository.findById(rollingPaperPieceReq.getRollingPaperUid());
        if (rollingPaper.isEmpty()) {
            throw new RollingPaperNotFoundException();
        }

        // check if the member is already participating in the event
        if (eventParticipantRepository.findByMemberUidAndEventUid(memberUid, eventUid) == null) {
                EventParticipant eventParticipant = toEventParticipantEntity(event.get(), member.get());
                eventParticipantRepository.save(eventParticipant);
                memberBadgeInfoRepository.updateParticipateCount(memberUid);
        }

        RollingPaperPiece rollingPaperPiece = toRollingPaperPieceEntity(member.get(), rollingPaper.get(), rollingPaperPieceReq);
        rollingPaperPieceRepository.save(rollingPaperPiece);
        return rollingPaperPiece.getUid();
    }

    @Override
    @Transactional
    public Long addRollingPaperPiece(Long eventUid, @Valid final RollingPaperPieceReq rollingPaperPieceReq) {
        Optional<Event> event = eventRepository.findById(eventUid);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }

        Optional<RollingPaper> rollingPaper = rollingPaperRepository.findById(rollingPaperPieceReq.getRollingPaperUid());
        if (rollingPaper.isEmpty()) {
            throw new RollingPaperNotFoundException();
        }

        RollingPaperPiece rollingPaperPiece = toRollingPaperPieceEntity(rollingPaper.get(), rollingPaperPieceReq);
        rollingPaperPieceRepository.save(rollingPaperPiece);
        return rollingPaperPiece.getUid();
    }

    @Override
    @Transactional
    public boolean isExistByRollingPaperPieceUid(Long rollingPaperPieceUid) {
        return rollingPaperPieceRepository.existsById(rollingPaperPieceUid);
    }

    RollingPaperPiece toRollingPaperPieceEntity(RollingPaper rollingPaper, RollingPaperPieceReq rollingPaperPieceReq) {
        return RollingPaperPiece.builder()
                .rollingPaper(rollingPaper)
                .content(rollingPaperPieceReq.getContent())
                .writerName(rollingPaperPieceReq.getWriterName())
                .content(rollingPaperPieceReq.getContent())
                .bgColor(rollingPaperPieceReq.getBgColor())
                .fontFamily(rollingPaperPieceReq.getFontFamily())
                .textColor(rollingPaperPieceReq.getTextColor())
                .textAlign(rollingPaperPieceReq.getTextAlign()).build();
    }

    RollingPaperPiece toRollingPaperPieceEntity(Member member, RollingPaper rollingPaper, RollingPaperPieceReq rollingPaperPieceReq) {
        return RollingPaperPiece.builder()
                .rollingPaper(rollingPaper)
                .member(member)
                .content(rollingPaperPieceReq.getContent())
                .writerName(rollingPaperPieceReq.getWriterName())
                .content(rollingPaperPieceReq.getContent())
                .bgColor(rollingPaperPieceReq.getBgColor())
                .fontFamily(rollingPaperPieceReq.getFontFamily())
                .textColor(rollingPaperPieceReq.getTextColor())
                .textAlign(rollingPaperPieceReq.getTextAlign()).build();
    }

    EventParticipant toEventParticipantEntity(Event event, Member member) {
        return EventParticipant.builder().event(event).member(member).build();
    }
}
