package nyongnyong.pangparty.service.feed;

public interface PostLikeService {

    void addPostLike(Long memberUid, Long postUid);

    void deletePostLike(Long memberUid, Long postUid);
}
