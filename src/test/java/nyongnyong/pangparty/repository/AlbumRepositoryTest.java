package nyongnyong.pangparty.repository;

import nyongnyong.pangparty.entity.album.Album;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.repository.album.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AlbumRepositoryTest {

    @Autowired
    AlbumRepository albumRepository;

    @Test
    public void testAlbum(){
//        Album album = new Album();
    }
}
