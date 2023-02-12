package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface AlbumMediaService {

    /**
     * 앨범 미디어 생성
     * @param eventUid
     * @param memberUid
     * @param thumbnailUrl
     * @param mediaUrl
     * @return AlbumMediaSimpleRes
     */
    public AlbumMediaSimpleRes createAlbumMedia(Long eventUid, Long memberUid, String thumbnailUrl, String mediaUrl);

    /**
     * 앨범 미디어 상세 조회
     * @param eventUid
     * @return AlbumMediaDetailRes
     */
    public AlbumMediaDetailRes getAlbumMedia(Long eventUid);

    /**
     * 앨범 미디어 목록 조회
     * @param albumUid
     * @param pageable
     * @return
     */
    public Page<AlbumMediaSimpleRes> getAlbumMediaList(Long albumUid, Pageable pageable);

    /**
     * 앨범 미디어 삭제
     * @param albumMediaUid
     */
    public void deleteAlbumMedia(Long albumMediaUid);

    /**
     * 앨범 미디어 소유자 확인
     * @param memberUid
     * @param mediaUid
     * @return
     */
    public boolean isAlbumMediaOwner(Long memberUid, Long mediaUid);
}
