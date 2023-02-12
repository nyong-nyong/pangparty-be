package nyongnyong.pangparty.service.feed;

import nyongnyong.pangparty.dto.feed.PostReq;
import nyongnyong.pangparty.dto.feed.PostRes;

public interface PostService {

    PostRes getPost(Long postUid, Long memberUid);

    Long addPost(PostReq postReq, Long memberUid);

    void deletePost(Long postUid, Long memberUid);

    void updatePost(Long postUid, Long memberUid, PostReq postReq);

}
