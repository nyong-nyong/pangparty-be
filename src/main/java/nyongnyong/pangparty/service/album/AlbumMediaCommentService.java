package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.dto.album.AlbumMediaCommentDto;
import nyongnyong.pangparty.entity.album.AlbumMediaComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AlbumMediaCommentService {

    /**
     * 앨범 미디어 댓글 생성
     * @param albumMediaCommentDto
     * @return
     */
    public AlbumMediaCommentDto createAlbumMediaComment(AlbumMediaCommentDto albumMediaCommentDto);

    /**
     * 앨범 미디어 댓글 조회
     * @param albumMediaCommentUid
     * @return
     */
    public AlbumMediaCommentDto getAlbumMediaComment(Long albumMediaCommentUid);

    /**
     * 앨범 미디어 댓글 목록 조회
     * @param albumMediaUid
     * @return
     */
    public Page<AlbumMediaCommentDto> getAlbumMediaCommentList(Long albumMediaUid, PageRequest pageRequest  );

    /**
     * 앨범 미디어 댓글 삭제
     * @param albumMediaCommentUid
     */
    public void deleteAlbumMediaComment(Long albumMediaCommentUid);
}
