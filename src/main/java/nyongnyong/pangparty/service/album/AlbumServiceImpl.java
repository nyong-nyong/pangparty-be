package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.repository.album.AlbumRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }


    @Override
    public void createAlbum() {

    }

    @Override
    public void getAlbum() {

    }

    @Override
    public void deleteAlbum() {

    }
}
