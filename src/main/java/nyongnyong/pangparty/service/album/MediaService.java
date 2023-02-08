package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface MediaService {

    public AlbumMedia saveMedia(MultipartFile file);

    public void deleteMedia(Long albumMediaUid);

    public MultipartFile resizeMediaToSmall(MultipartFile file);

    public MultipartFile resizeMediaToThumbnail(MultipartFile file);

    public MultipartFile reformatMedia(MultipartFile file);

}
