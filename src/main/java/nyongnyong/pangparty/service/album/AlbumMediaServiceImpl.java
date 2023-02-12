package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.album.AlbumRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class AlbumMediaServiceImpl implements AlbumMediaService {

    private final AlbumMediaRepository albumMediaRepository;
    private final AlbumRepository albumRepository;
    private final MemberRepository memberRepository;

    public AlbumMediaServiceImpl(AlbumMediaRepository albumMediaRepository, AlbumRepository albumRepository, MemberRepository memberRepository) {
        this.albumMediaRepository = albumMediaRepository;
        this.albumRepository = albumRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public AlbumMediaSimpleRes createAlbumMedia(Long eventUid, Long memberUid, String thumbnailUrl, String mediaUrl) {
        AlbumMedia albumMedia = AlbumMedia.builder()
                .album(albumRepository.findByEventUid(eventUid))
                .member(memberRepository.findMemberByUid(memberUid))
                .thumbnailUrl(thumbnailUrl)
                .mediaUrl(mediaUrl)
                .extension(mediaUrl.substring(mediaUrl.lastIndexOf(".")+1))
                .uploadTime(LocalDateTime.now())
                .build();
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
        Page<AlbumMedia> albumMediaPage = albumMediaRepository.findByUidGreaterThanOrderByUidAsc(albumUid, pageable);
        Page<AlbumMediaSimpleRes> dtoPage = albumMediaPage.map(m -> new AlbumMediaSimpleRes(m));
        log.debug("get albumMediaDtoPage = " + dtoPage);
        return dtoPage;
    }

    @Override
    public void deleteAlbumMedia(Long albumMediaUid) {
        StringBuilder sb = new StringBuilder();
        try {
            albumMediaRepository.findById(albumMediaUid).ifPresent(albumMedia -> {
                sb.append(albumMedia.getAlbum().getUid())
                        .append("/").append(albumMediaUid).append(".webp");
                String key = sb.toString();
                albumMediaRepository.deleteById(albumMediaUid);
                log.debug("deleted albumMediaUid = " + albumMediaUid);
            });
        } catch (NoSuchElementException e) {
            log.debug("albumMediaUid = " + albumMediaUid + " is not exist");
        }
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
