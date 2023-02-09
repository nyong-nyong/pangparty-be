package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.dto.album.AlbumMediaCommentSimpleRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface AlbumMediaCommentService {

    /**
     * 앨범 미디어 댓글 생성
     * @param memberUid
     * @param comment
     * @return
     */
    public AlbumMediaCommentSimpleRes createAlbumMediaComment(Long memberUid, String comment);

    /**
     * 앨범 미디어 댓글 목록 조회
     * @param albumMediaUid
     * @param pageable
     * @return
     */
    public Page<AlbumMediaCommentSimpleRes> getAlbumMediaCommentList(Long albumMediaUid, Pageable pageable);

    /**
     * 앨범 미디어 댓글 삭제
     * @param albumMediaCommentUid
     */
    public void deleteAlbumMediaComment(Long albumMediaCommentUid);
}
