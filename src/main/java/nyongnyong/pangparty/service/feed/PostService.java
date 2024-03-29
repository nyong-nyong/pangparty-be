package nyongnyong.pangparty.service.feed;

import nyongnyong.pangparty.dto.feed.FeedRes;
import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.dto.feed.PostReq;
import nyongnyong.pangparty.dto.feed.PostRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    PostRes getPost(Long postUid, Long memberUid);

    Long addPost(PostReq postReq, Long memberUid);

    void deletePost(Long postUid, Long memberUid);

    void updatePost(Long postUid, Long memberUid, PostReq postReq);

    Page<FeedRes> getFeed(Long memberUid, Pageable pageable);
    Page<FeedRes> findProfileFeed(Long memberUid, String memberId, Pageable pageable);
}
