package nyongnyong.pangparty.service.feed;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.entity.feed.Post;
import nyongnyong.pangparty.entity.feed.PostLike;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.PostNotFoundException;
import nyongnyong.pangparty.repository.feed.PostLikeRepository;
import nyongnyong.pangparty.repository.feed.PostRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostLikeServiceImpl implements PostLikeService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    @Override
    @Transactional
    public void addPostLike(Long memberUid, Long postUid) {
        Member member = memberRepository.findMemberByUid(memberUid);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        Optional<Post> post = postRepository.findByUid(postUid);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }

        Optional<PostLike> postLike = postLikeRepository.findByPostUidAndMemberUid(postUid, memberUid);
        if (postLike.isPresent()) {
            throw new IllegalStateException("이미 좋아요를 누른 게시물입니다.");
        }

        PostLike createPostLike = PostLike.builder()
                .post(post.get())
                .member(member)
                .build();

        postLikeRepository.save(createPostLike);
    }

    @Override
    @Transactional
    public void deletePostLike(Long memberUid, Long postUid) {
        Member member = memberRepository.findMemberByUid(memberUid);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        Optional<Post> post = postRepository.findByUid(postUid);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }

        Optional<PostLike> postLike = postLikeRepository.findByPostUidAndMemberUid(postUid, memberUid);
        if (postLike.isEmpty()) {
            throw new IllegalStateException("좋아요를 누른 적 없는 게시물입니다.");
        }

        postLikeRepository.delete(postLike.get());
    }
}
