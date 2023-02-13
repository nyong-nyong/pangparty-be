package nyongnyong.pangparty.service.feed;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.feed.PostCommentRes;
import nyongnyong.pangparty.dto.feed.PostReq;
import nyongnyong.pangparty.dto.feed.PostRes;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.feed.Post;
import nyongnyong.pangparty.entity.feed.PostComment;
import nyongnyong.pangparty.entity.feed.PostLike;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.exception.CommentNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.PostNotFoundException;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.feed.PostCommentRepository;
import nyongnyong.pangparty.repository.feed.PostLikeRepository;
import nyongnyong.pangparty.repository.feed.PostRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostCommentRepository postCommentRepository;

    @Override
    @Transactional
    public PostRes getPost(Long postUid, Long memberUid) {
        PostRes postRes = postRepository.findPostResByUid(postUid);
        if (postRes != null) {
            List<PostCommentRes> postCommentResList = postCommentRepository.findAllByPostUid(postUid, Pageable.ofSize(5)).getContent();
            postRes.setPostComments(postCommentResList);
            postRes.setLikeCount(postLikeRepository.countByPostUid(postUid));

            if (memberUid != null) {
                Optional<PostLike> postLike = postLikeRepository.findByPostUidAndMemberUid(postUid, memberUid);
                if (postLike != null) {
                    postRes.setLiked(true);
                }
            }

            return postRes;
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    @Transactional
    public Long addPost(PostReq postReq, Long memberUid) {
        // check if member exists
        Member member = memberRepository.findMemberByUid(memberUid);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        if (postReq.getEventUid() != null) {
            Event event = eventRepository.findEventByUid(postReq.getEventUid());
            Post post = toPostEntity(postReq, member, event);
            return postRepository.save(post).getUid();
        } else {
            Post post = toPostEntity(postReq, member);
            return postRepository.save(post).getUid();
        }
    }

    @Override
    @Transactional
    public void deletePost(Long postUid, Long memberUid) {
        Optional<Post> post = postRepository.findByUid(postUid);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }

        Member member = memberRepository.findMemberByUid(memberUid);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        if (!post.get().getMember().getUid().equals(memberUid)) {
            throw new IllegalArgumentException("사용자가 작성하지 않은 게시물");
        }

        postRepository.delete(post.get());
    }

    @Override
    @Transactional
    public void updatePost(Long postUid, Long memberUid, PostReq postReq) {
        Optional<Post> post = postRepository.findByUid(postUid);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }

        Member member = memberRepository.findMemberByUid(memberUid);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        if (!post.get().getMember().getUid().equals(memberUid)) {
            throw new IllegalArgumentException("사용자가 작성하지 않은 게시물");
        }

        Event event = null;
        if (postReq.getEventUid() != null) {
            event = eventRepository.findEventByUid(postReq.getEventUid());
        }

        postRepository.updatePost(postUid, event, postReq.getTitle(), postReq.getContent(), postReq.getImgUrl());
    }

    @Override
    public Page<PostRes> getFeed(Long memberUid, Pageable pageable) {
        try{
            Page<PostRes> feed = postRepository.findPostsByMemberUid(memberUid, pageable);
            if(feed.getTotalPages() > 0){
                return feed;
            } else{
                System.out.println("feed is empty1");
                throw new PostNotFoundException();
            }
        } catch(Exception e){
            System.out.println("feed is empty2");
            throw new PostNotFoundException();
        }
    }

    Post toPostEntity(PostReq postReq, Member member) {
        return Post.builder()
                .member(member)
                .title(postReq.getTitle())
                .imgUrl(postReq.getImgUrl())
                .content(postReq.getContent())
                .build();
    }

    Post toPostEntity(PostReq postReq, Member member, Event event) {
        return Post.builder()
                .member(member)
                .event(event)
                .title(postReq.getTitle())
                .imgUrl(postReq.getImgUrl())
                .content(postReq.getContent())
                .build();
    }

    PostComment toPostCommentEntity(Long postUid, Long memberUid, String content) {
        return PostComment.builder()
                .post(postRepository.findByUid(postUid).get())
                .member(memberRepository.findMemberByUid(memberUid))
                .content(content)
                .build();
    }

    PostRes toPostRes(Post post) {
        return PostRes.builder().build();
    }
}
