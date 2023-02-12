package nyongnyong.pangparty.service.feed;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.entity.feed.PostComment;
import nyongnyong.pangparty.exception.CommentNotFoundException;
import nyongnyong.pangparty.exception.PostNotFoundException;
import nyongnyong.pangparty.repository.feed.PostCommentRepository;
import nyongnyong.pangparty.repository.feed.PostRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<PostCommentRes> getPostCommentList(Long postUid, Pageable pageable) {
        // check if post exists
        if (!postRepository.existsByUid(postUid)) {
            throw new PostNotFoundException();
        }

        return postCommentRepository.findAllByPostUid(postUid, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostCommentRes> getRecentCommentList(Long postUid, int count) {
        // check if post exists
        if (!postRepository.existsByUid(postUid)) {
            throw new PostNotFoundException();
        }

        return postCommentRepository.findAllByPostUid(postUid, Pageable.ofSize(count));
    }

    @Override
    @Transactional
    public Long createPostComment(Long postUid, Long memberUid, String content) {
        // check if post exists
        if (!postRepository.existsByUid(postUid)) {
            throw new PostNotFoundException();
        }

        PostComment postComment = toPostCommentEntity(postUid, memberUid, content);
        postCommentRepository.save(postComment);

        return postComment.getUid();
    }

    @Override
    @Transactional
    public void deletePostComment(Long postUid, Long commentUid, Long memberUid) {
        // check if post exists
        if (!postRepository.existsByUid(postUid)) {
            throw new PostNotFoundException();
        }

        // check if comment exists
        Optional<PostComment> postComment = postCommentRepository.findByUid(commentUid);
        if (postComment.isEmpty()) {
            throw new CommentNotFoundException();
        }

        // check if comment is written by member
        if (!postComment.get().getMember().getUid().equals(memberUid)) {
            throw new IllegalArgumentException("사용자가 작성하지 않은 댓글");
        }

        postCommentRepository.delete(postComment.get());
    }

    PostComment toPostCommentEntity(Long postUid, Long memberUid, String content) {
        return PostComment.builder()
                .post(postRepository.findByUid(postUid).get())
                .member(memberRepository.findMemberByUid(memberUid))
                .content(content)
                .build();
    }

}
