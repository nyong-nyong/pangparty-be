package nyongnyong.pangparty.repository.album;

import nyongnyong.pangparty.entity.album.AlbumMediaLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumMediaLikeRepository extends JpaRepository<AlbumMediaLike, Long> {

    /**
     * 앨범 미디어 좋아요 취소
     * @param albumMediaUid
     * @param memberUid
     */
    public void deleteByAlbumMediaUidAndMemberUid(Long albumMediaUid, Long memberUid);

    AlbumMediaLike findByAlbumMediaUidAndMemberUid(Long mediaUid, Long memberUid);
}
