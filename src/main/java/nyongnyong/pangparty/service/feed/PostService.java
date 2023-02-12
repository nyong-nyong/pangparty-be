package nyongnyong.pangparty.service.feed;

import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.dto.feed.PostReq;
import nyongnyong.pangparty.dto.feed.PostRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostRes getPost(Long postUid, Long memberUid);

    Long addPost(PostReq postReq, Long memberUid);

    void deletePost(Long postUid, Long memberUid);

    void updatePost(Long postUid, Long memberUid, PostReq postReq);

    Page<PostCommentRes> getPostCommentList(Long postUid, Pageable pageable);

    Page<PostCommentRes> getRecentCommentList(Long postUid, int count);

    Long createPostComment(Long postUid, Long memberUid, String content);

    void deletePostComment(Long postUid, Long commentUid, Long memberUid);
}
