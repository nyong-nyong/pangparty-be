package nyongnyong.pangparty.service.album;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.entity.event.EventParticipant;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.album.AlbumRepository;
import nyongnyong.pangparty.repository.event.EventParticipantRepository;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperPieceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumMediaServiceImpl implements AlbumMediaService {
    private final RollingPaperPieceRepository rollingPaperPieceRepository;
    private final EventRepository eventRepository;

    private final AlbumMediaRepository albumMediaRepository;
    private final AlbumRepository albumRepository;
    private final MemberRepository memberRepository;
    private final EventParticipantRepository eventParticipantRepository;

    @Override
    public AlbumMediaSimpleRes createAlbumMedia(Long eventUid, Long memberUid, String thumbnailUrl, String mediaUrl) throws NoSuchElementException {
        AlbumMedia albumMedia = AlbumMedia.builder()
                .album(albumRepository.findByEventUid(eventUid))
                .member(memberRepository.findMemberByUid(memberUid))
                .thumbnailUrl(thumbnailUrl)
                .mediaUrl(mediaUrl)
                .extension(mediaUrl.substring(mediaUrl.lastIndexOf(".")+1))
                .uploadTime(LocalDateTime.now())
                .build();
        // check if the member is already participating in the event
        if (eventParticipantRepository.findByMemberUidAndEventUid(memberUid, eventUid) == null) {
            EventParticipant eventParticipant = EventParticipant.builder().event(eventRepository.findById(eventUid).get()).member(memberRepository.findById(memberUid).get()).build();
            eventParticipantRepository.save(eventParticipant);
        }
        return new AlbumMediaSimpleRes(albumMediaRepository.save(albumMedia));
    }

    @Override
    public AlbumMediaDetailRes getAlbumMedia(Long mediaUid) {
        try {
            List<List<Long>> prevAndNext = albumMediaRepository.findPrevAndNextByUid(mediaUid);
            log.debug("get prevAndNext = " + prevAndNext.get(0));
            Optional<AlbumMedia> medium = albumMediaRepository.findById(mediaUid);
            if(medium.isPresent()){
                AlbumMediaDetailRes albumMediaDetailRes = new AlbumMediaDetailRes(medium.get(), prevAndNext.get(0).get(0), prevAndNext.get(0).get(1));
                log.debug("get albumMediaDetailRes = " + albumMediaDetailRes);
                return albumMediaDetailRes;
            } else{
                return null;
            }
        }catch (NoSuchElementException e){
            log.error("NoSuchElementException = " + e);
            return null;
        }
    }

    @Override
    public Page<AlbumMediaSimpleRes> getAlbumMediaList(Long albumUid, Pageable pageable) {
        Page<AlbumMedia> albumMediaPage = albumMediaRepository.findByAlbumUidOrderByUidAsc(albumUid, pageable);
        Page<AlbumMediaSimpleRes> dtoPage = albumMediaPage.map(AlbumMediaSimpleRes::new);
        log.debug("get albumMediaDtoPage = " + dtoPage.getContent());
        return dtoPage;
    }

    @Override
    public void deleteAlbumMedia(Long memberUid, Long albumMediaUid) throws NoSuchElementException {
        albumMediaRepository.deleteById(albumMediaUid);
        log.debug("deleted albumMediaUid = " + albumMediaUid);
    }

    @Override
    public boolean isAlbumMediaOwner(Long memberUid, Long mediaUid) {
        try {
            if (albumMediaRepository.findById(mediaUid).get().getMember().getUid().equals(memberUid)) {
                log.debug("albumMediaUid = " + mediaUid + " is owner");
                return true;
            }else {
                log.debug("albumMediaUid = " + mediaUid + " is not owner");
                return false;
            }
        } catch (NoSuchElementException e) {
            log.debug("albumMediaUid = " + mediaUid + " is not exist");
            return false;
        }
    }

}
