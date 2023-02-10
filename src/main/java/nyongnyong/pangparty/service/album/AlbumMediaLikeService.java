package nyongnyong.pangparty.service.album;

public interface AlbumMediaLikeService {

    /**
     * 앨범 미디어 좋아요
     * @param albumMediaUid
     * @param memberUid
     */
    public void likeAlbumMedia(Long albumMediaUid, Long memberUid);

    /**
     * 앨범 미디어 좋아요 취소
     * @param albumMediaUid
     * @param memberUid
     */
    public void unlikeAlbumMedia(Long albumMediaUid, Long memberUid);
}
