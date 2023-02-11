package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.util.AwsS3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class AlbumMediaServiceImpl implements AlbumMediaService {

    private final AlbumMediaRepository albumMediaRepository;
    private final AwsS3 awsS3 = AwsS3.getInstance();

    public AlbumMediaServiceImpl(AlbumMediaRepository albumMediaRepository) {
        this.albumMediaRepository = albumMediaRepository;
    }

    @Override
    public AlbumMediaSimpleRes createAlbumMedia(AlbumMedia albumMedia) {
        //TODO: create albumMedia
        return null;
    }

    @Override
    public AlbumMediaDetailRes getAlbumMedia(Long mediaUid) {
        try {
            Long[] prevAndNext = albumMediaRepository.findPrevAndNextByUid(mediaUid);
            Optional<AlbumMedia> medium = albumMediaRepository.findById(mediaUid);
            if(medium.isPresent()){
                AlbumMediaDetailRes albumMediaDetailRes = new AlbumMediaDetailRes(medium.get(), prevAndNext[0], prevAndNext[1]);
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
                awsS3.delete("thumbnail/" + key);
                log.debug("deleted albumMediaKey = " + key);
                awsS3.delete("albumMedia/" + key + " at /thumbnail/");
                log.debug("deleted albumMediaKey = " + key + " at /albumMedia/");
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
