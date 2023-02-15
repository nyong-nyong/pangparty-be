package nyongnyong.pangparty.repository.album;

import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumMediaRepository extends JpaRepository<AlbumMedia, Long> {
    Page<AlbumMedia> findByAlbumUid(Long albumUid, Pageable pageable);

    int countByMemberUidAndAlbumUid(Long memberUid, Long albumUid);
    Page<AlbumMedia> findByAlbumUidOrderByUidAsc(Long albumUid, Pageable pageable);
    List<AlbumMedia> findByAlbumUidOrderByUidAsc(Long albumUid);

    @Query(value = "SELECT prevUid, nextUid from (SELECT LAG(uid, 1) OVER(ORDER BY uid) as prevUid, uid, LEAD(uid, 1) OVER(ORDER BY uid) as nextUid FROM album_media) as sub WHERE sub.uid = :uid",
            nativeQuery = true)
    List<List<Long>> findPrevAndNextByUid(Long uid);

}