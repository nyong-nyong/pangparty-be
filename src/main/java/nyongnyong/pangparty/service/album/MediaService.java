package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.common.MediaSizeType;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    /**
     * S3에 미디어 업로드
     * @param file
     * @param key
     * @param contentType
     * @param contentLength
     * @return
     */
    public String uploadMedia(MultipartFile file, String key, String contentType, long contentLength);

    /**
     * S3에서 미디어 삭제
     * @param key
     */
    public void deleteMedia(String key);

    /**
     * 미디어 앨범 상세 크기로 리사이징
     * @param file
     * @return
     */
    public MultipartFile resizeMediaToAlbumSize(MultipartFile file);

    /**
     * 미디어 thumbnail 크기로 리사이징
     * @param file
     * @return
     */
    public MultipartFile resizeMediaToThumbnail(MultipartFile file);

    /**
     * 미디어 webp로 변환
     * @param file
     * @return
     */
    public MultipartFile reformatMedia(MultipartFile file);

}
