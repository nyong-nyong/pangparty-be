package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaCommentDto;
import nyongnyong.pangparty.repository.album.AlbumMediaCommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlbumMediaCommentServiceImpl implements AlbumMediaCommentService {

    private final AlbumMediaCommentRepository albumMediaCommentRepository;

    public AlbumMediaCommentServiceImpl(AlbumMediaCommentRepository albumMediaCommentRepository) {
        this.albumMediaCommentRepository = albumMediaCommentRepository;
    }

    @Override
    public AlbumMediaCommentDto createAlbumMediaComment(AlbumMediaCommentDto albumMediaCommentDto) {
        return null;
    }

    @Override
    public AlbumMediaCommentDto getAlbumMediaComment(Long albumMediaCommentUid) {
        return null;
    }

    @Override
    public Page<AlbumMediaCommentDto> getAlbumMediaCommentList(Long albumMediaUid, PageRequest pageRequest) {
        return null;
    }

    @Override
    public void deleteAlbumMediaComment(Long albumMediaCommentUid) {

    }
}
