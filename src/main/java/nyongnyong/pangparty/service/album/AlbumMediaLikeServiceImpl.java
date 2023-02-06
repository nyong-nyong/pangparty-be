package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.repository.album.AlbumMediaLikeRepository;

public class AlbumMediaLikeServiceImpl implements AlbumMediaLikeService{

    private final AlbumMediaLikeRepository albumMediaLikeRepository;

    public AlbumMediaLikeServiceImpl(AlbumMediaLikeRepository albumMediaLikeRepository) {
        this.albumMediaLikeRepository = albumMediaLikeRepository;
    }


    @Override
    public int createAlbumMediaLike(Long albumMediaId, Long userId) {
        return 0;
    }

    @Override
    public int getAlbumMediaLike(Long albumMediaId) {
        return 0;
    }

    @Override
    public boolean getAlbumMediaLike(Long albumMediaId, Long userId) {
        return false;
    }

    @Override
    public void deleteAlbumMediaLike(Long albumMediaId, Long userId) {

    }
}
