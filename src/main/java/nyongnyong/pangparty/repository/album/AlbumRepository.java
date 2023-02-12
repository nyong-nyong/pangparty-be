package nyongnyong.pangparty.repository.album;

import nyongnyong.pangparty.entity.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long>, AlbumRepositoryCustom {
    public Album findByEventUid(Long eventUid);
}
