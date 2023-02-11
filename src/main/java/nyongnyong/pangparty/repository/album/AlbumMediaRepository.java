package nyongnyong.pangparty.repository.album;

import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface AlbumMediaRepository extends JpaRepository<AlbumMedia, Long> {
    Page<AlbumMedia> findByAlbumUid(Long albumUid, Pageable pageable);
    Page<AlbumMedia> findByUidGreaterThanOrderByUidAsc(Long uid, Pageable pageable);

    @Query(value = "select * from (SELECT LAG(uid, 1) OVER(ORDER BY uid)," +
            "LEAD(uid, 1) OVER(ORDER BY uid)" +
            "FROM album_media) sub where sub.uid = :uid",
            nativeQuery = true)
    Long[] findPrevAndNextByUid(Long uid);
}