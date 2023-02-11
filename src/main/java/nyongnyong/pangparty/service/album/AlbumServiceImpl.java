package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.entity.album.Album;
import nyongnyong.pangparty.repository.album.AlbumRepository;
import nyongnyong.pangparty.repository.event.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService{

    private final EventRepository eventRepository;
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(EventRepository eventRepository, AlbumRepository albumRepository) {
        this.eventRepository = eventRepository;
        this.albumRepository = albumRepository;
    }


    @Override
    public Album createAlbum(Long eventUid) {
        return albumRepository.save(Album.builder().event(eventRepository.findById(eventUid).get()).build());
    }

    @Override
    public Album getAlbum(Long eventUid) {
        return eventRepository.findById(eventUid).get().getAlbum();
    }

    @Override
    public void deleteAlbum(Long albumUid) {
        albumRepository.deleteById(albumUid);
    }
}
