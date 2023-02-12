package nyongnyong.pangparty.service.feed;

import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.dto.feed.PostReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Long addPost(PostReq postReq, Long memberUid);

    Page<PostCommentRes> getPostCommentList(Long postUid, Pageable pageable);

    Page<PostCommentRes> getRecentCommentList(Long postUid, int count);

    Long createPostComment(Long postUid, Long memberUid, String content);

    void deletePostComment(Long postUid, Long commentUid, Long memberUid);

}
