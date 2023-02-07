package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaCommentSimpleRes;
import nyongnyong.pangparty.repository.album.AlbumMediaCommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlbumMediaCommentServiceImpl implements AlbumMediaCommentService {

    private final AlbumMediaCommentRepository albumMediaCommentRepository;

    public AlbumMediaCommentServiceImpl(AlbumMediaCommentRepository albumMediaCommentRepository) {
        this.albumMediaCommentRepository = albumMediaCommentRepository;
    }

    @Override
    public AlbumMediaCommentSimpleRes createAlbumMediaComment(AlbumMediaCommentSimpleRes albumMediaCommentSimpleRes) {
        return null;
    }

    @Override
    public Page<AlbumMediaCommentSimpleRes> getAlbumMediaCommentList(Long albumMediaUid, PageRequest pageRequest) {
        return null;
    }

    @Override
    public void deleteAlbumMediaComment(Long albumMediaCommentUid) {

    }
}
