package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.dto.album.AlbumMediaCommentSimpleRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;

public interface AlbumMediaCommentService {

    /**
     * 앨범 미디어 댓글 생성
     * @param memberUid 댓글 작성자 UID
     * @param albumMediaUid 앨범 미디어 UID
     * @param comment 댓글 내용
     * @return
     */
    public AlbumMediaCommentSimpleRes createAlbumMediaComment(Long memberUid, Long albumMediaUid, String comment);

    /**
     * 앨범 미디어 댓글 목록 조회
     * @param albumMediaUid 앨범 미디어 UID
     * @param pageable 페이징 정보
     * @return
     */
    public Page<AlbumMediaCommentSimpleRes> getAlbumMediaCommentList(Long albumMediaUid, Pageable pageable);

    /**
     * 앨범 미디어 댓글 삭제
     * @param memberUid 삭제 요청자 UID
     * @param albumMediaCommentUid 앨범 댓글 UID
     *
     */
    public void deleteAlbumMediaComment(Long memberUid, Long albumMediaCommentUid);

    /**
     * 앨범 미디어 댓글 작성자 확인
     * @param memberUid 작성자 UID
     * @param albumMediaCommentUid 앨범 댓글 UID
     * @return
     * @throws NoSuchElementException
     */
    public boolean isAlbumMediaCommentOwner(Long memberUid, Long albumMediaCommentUid) throws NoSuchElementException;
}
