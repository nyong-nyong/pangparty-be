package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.event.EventParticipant;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.event.EventParticipantRepository;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperPieceRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperStickerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventParticipantServiceImpl implements EventParticipantService {

    private final EventParticipantRepository eventParticipantRepository;
    private final AlbumMediaRepository albumMediaRepository;
    private final EventRepository eventRepository;
    private final RollingPaperPieceRepository rollingPaperPieceRepository;
    private final RollingPaperStickerRepository rollingPaperStickerRepository;

    @Override
    public EventParticipant findEventParticipantByMemberUidAndEventUid(Long memberUid, Long eventUid) {
        return eventParticipantRepository.findByMemberUidAndEventUid(memberUid, eventUid);
    }

    @Override
    public void deleteEventParticipant(Long memberUid, Long eventUid) {
        eventParticipantRepository.deleteByMemberUidAndEventUid(memberUid, eventUid);
    }

    @Override
    public boolean isEventParticipant(Long memberUid, Long eventUid) {
        Event event = eventRepository.findById(eventUid).get();
        Long albumUid = event.getAlbum().getUid();
        Long rollingPaperUid = event.getRollingPaper().getUid();
        int mediaCnt = albumMediaRepository.countByMemberUidAndAlbumUid(memberUid, albumUid);
        log.debug("mediaCnt: {}", mediaCnt);
        int pieceCnt = rollingPaperPieceRepository.countByMemberUidAndRollingPaperUid(memberUid, rollingPaperUid);
        log.debug("pieceCnt: {}", pieceCnt);
        int stickerCnt = rollingPaperStickerRepository.countByMemberUidAndRollingPaperUid(memberUid, rollingPaperUid);
        log.debug("stickerCnt: {}", stickerCnt);
        return mediaCnt > 0 || pieceCnt > 0 || stickerCnt > 0;
    }

}
