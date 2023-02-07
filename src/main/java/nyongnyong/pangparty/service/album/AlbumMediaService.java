package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.dto.album.AlbumMediaDetailRes;
import nyongnyong.pangparty.dto.album.AlbumMediaSimpleRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface AlbumMediaService {

    /**
     * 앨범 미디어 생성
     * @param albumMediaDetailRes
     * @return AlbumMediaSimpleRes
     */
    public AlbumMediaSimpleRes createAlbumMedia(AlbumMediaDetailRes albumMediaDetailRes);

    /**
     * 앨범 미디어 상세 조회
     * @param eventUid
     * @return AlbumMediaDetailRes
     */
    public AlbumMediaDetailRes getAlbumMedia(Long eventUid);

    /**
     * 앨범 미디어 목록 조회
     * @param albumUid
     * @param pageRequest
     * @return
     */
    public Page<AlbumMediaSimpleRes> getAlbumMediaList(Long albumUid, PageRequest pageRequest);

    /**
     * 앨범 미디어 삭제
     * @param albumMediaUid
     */
    public void deleteAlbumMedia(Long albumMediaUid);
}
