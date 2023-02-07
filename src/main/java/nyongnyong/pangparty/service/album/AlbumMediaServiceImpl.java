package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlbumMediaServiceImpl implements AlbumMediaService {

    private final AlbumMediaRepository albumMediaRepository;

    public AlbumMediaServiceImpl(AlbumMediaRepository albumMediaRepository) {
        this.albumMediaRepository = albumMediaRepository;
    }

    @Override
    public AlbumMediaSimpleRes createAlbumMedia(AlbumMediaDetailRes albumMediaDetailRes) {
        return null;
    }

    @Override
    public AlbumMediaDetailRes getAlbumMedia(Long eventUid) {
        AlbumMedia medium = albumMediaRepository.findById(eventUid).orElse(null);
        return null;
    }

    @Override
    public Page<AlbumMediaSimpleRes> getAlbumMediaList(Long albumUid, PageRequest pageRequest) {
        Page<AlbumMedia> albumMediaPage = albumMediaRepository.findAll(pageRequest);
        Page<AlbumMediaSimpleRes> dtoPage = albumMediaPage.map(m -> new AlbumMediaSimpleRes());
        log.debug("get albumMediaDtoPage = " + dtoPage);
        return dtoPage;
    }

    @Override
    public void deleteAlbumMedia(Long albumMediaUid) {
        log.debug("delete albumMediaUid = " + albumMediaUid);
    }

}
