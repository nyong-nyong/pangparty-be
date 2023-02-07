package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.entity.album.Album;

public interface AlbumService {

    /**
     * 앨범 생성
     * @param eventUid
     * @return albumUid
     */
    public Long createAlbum(Long eventUid);

    /**
     * 앨범 조회
     * @param eventUid
     * @return albumUid
     */
    public Long getAlbumUid(Long eventUid);

    /**
     * 앨범 삭제
     * @param albumUid
     */
    public void deleteAlbum(Long albumUid);

}
