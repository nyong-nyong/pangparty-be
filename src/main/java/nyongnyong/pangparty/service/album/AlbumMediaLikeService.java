package nyongnyong.pangparty.service.album;

public interface AlbumMediaLikeService {

    /**
     * 앨범 미디어 좋아요 생성
     * @param albumMediaId
     * @param userId
     * @return
     */
    public int createAlbumMediaLike(Long albumMediaId, Long userId);

    /**
     * 앨범 미디어 좋아요 조회
     * @param albumMediaId
     * @return
     */
    public int getAlbumMediaLike(Long albumMediaId);

    /**
     * 앨범 미디어 좋아요 여부 조회
     * @param albumMediaId
     * @param userId
     * @return
     */
    public boolean getAlbumMediaLike(Long albumMediaId, Long userId);

    /**
     * 앨범 미디어 좋아요 삭제
     * @param albumMediaId
     * @param userId
     */
    public void deleteAlbumMediaLike(Long albumMediaId, Long userId);
}
