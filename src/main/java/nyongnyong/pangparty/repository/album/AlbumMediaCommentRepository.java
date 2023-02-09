package nyongnyong.pangparty.repository.album;

import nyongnyong.pangparty.entity.album.AlbumMediaComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumMediaCommentRepository extends JpaRepository<AlbumMediaComment, Long> {
    public Page<AlbumMediaComment> findByAlbumMediaUid(Long albumMediaUid, Pageable pageable);
}
