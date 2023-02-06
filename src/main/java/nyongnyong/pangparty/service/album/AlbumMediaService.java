package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.dto.album.AlbumMediaDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlbumMediaService {
    public AlbumMediaDto createAlbumMedia();
    public AlbumMediaDto getAlbumMedia(Long eventUid);
    public Page<AlbumMediaDto> getAlbumMediaList();
    public void deleteAlbumMedia();
}
