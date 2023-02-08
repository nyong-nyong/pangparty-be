package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class MediaServiceImpl implements MediaService{
    @Override
    public AlbumMedia saveMedia(MultipartFile file) {
        return null;
    }

    @Override
    public void deleteMedia(Long albumMediaUid) {

    }

    @Override
    public MultipartFile resizeMediaToSmall(MultipartFile file) {
        return null;
    }

    @Override
    public MultipartFile resizeMediaToThumbnail(MultipartFile file) {
        return null;
    }

    @Override
    public MultipartFile reformatMedia(MultipartFile file) {
        return null;
    }
}
