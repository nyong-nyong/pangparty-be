package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaDto;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlbumMediaServiceImpl implements AlbumMediaService {

    private final AlbumMediaRepository albumMediaRepository;

    public AlbumMediaServiceImpl(AlbumMediaRepository albumMediaRepository) {
        this.albumMediaRepository = albumMediaRepository;
    }

    /**
     * @return
     */
    @Override
    public AlbumMediaDto createAlbumMedia() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public AlbumMediaDto getAlbumMedia(Long eventUid) {
        AlbumMedia medium = albumMediaRepository.findById(eventUid).orElse(null);
        return null;
    }

    /**
     * @return
     */
    @Override
    public Page<AlbumMediaDto> getAlbumMediaList() {
        List<AlbumMedia> media = albumMediaRepository.findAll();
        return null;
    }

    /**
     *
     */
    @Override
    public void deleteAlbumMedia() {

    }

}
