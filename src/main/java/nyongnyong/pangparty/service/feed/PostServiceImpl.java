package nyongnyong.pangparty.service.feed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.dto.feed.*;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.feed.Post;
import nyongnyong.pangparty.entity.feed.PostComment;
import nyongnyong.pangparty.entity.feed.PostLike;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.exception.FeedNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.exception.MemberUnAuthorizedException;
import nyongnyong.pangparty.exception.PostNotFoundException;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.feed.PostCommentRepository;
import nyongnyong.pangparty.repository.feed.PostLikeRepository;
import nyongnyong.pangparty.repository.feed.PostRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
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
//            List<PostCommentRes> postCommentResList = postCommentRepository.findAllByPostUid(postUid, Pageable.ofSize(5)).getContent();
//            postRes.setPostComments(postCommentResList);
            postRes.setLikeCount(postLikeRepository.countByPostUid(postUid));
            postRes.setCommentCount(postCommentRepository.countByPostUid(postUid));

            if (memberUid != null) {
                Optional<PostLike> postLike = postLikeRepository.findByPostUidAndMemberUid(postUid, memberUid);
                if (postLike.isPresent()) {
                    postRes.setLiked(true);
                }

                postRes.setHasCommented(postCommentRepository.existsByPostUidAndMemberUid(postUid, memberUid));
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
            throw new MemberUnAuthorizedException();
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
            throw new MemberUnAuthorizedException();
        }

        Event event = null;
        if (postReq.getEventUid() != null) {
            event = eventRepository.findEventByUid(postReq.getEventUid());
        }

        postRepository.updatePost(postUid, event, postReq.getTitle(), postReq.getContent(), postReq.getImgUrl());
    }

    @Override
    public Page<FeedRes> getFeed(Long memberUid, Pageable pageable) {
        try{
            List<FeedDto> feedDtoPages = postRepository.findPostsByMemberUid(memberUid);
            ArrayList<FeedRes> feedResList = new ArrayList<>();
            for (int i = 0; i < feedDtoPages.size(); i++) {
                FeedDto feedDto = feedDtoPages.get(i);
                feedDto.setLiked(postLikeRepository.existsByPostUidAndMemberUid(feedDto.getUid(), memberUid));
                feedDto.setLikeCnt(postLikeRepository.countByPostUid(feedDto.getUid()));
                feedDto.setHasCommented(postCommentRepository.existsByPostUidAndMemberUid(feedDto.getUid(), memberUid));
                feedDto.setCommentCnt(postCommentRepository.countByPostUid(feedDto.getUid()));
                EventCard eventCard = eventRepository.findEventCardByEventUid(feedDto.getEventUid());
                FeedRes feedRes = new FeedRes(feedDto.getUid(), eventCard, feedDto.getMemberId(), feedDto.isLiked(), feedDto.getLikeCnt(), feedDto.isHasCommented(), feedDto.getCommentCnt(), feedDto.getTitle(), feedDto.getContent(), feedDto.getImgUrl(), feedDto.getProfileImgUrl(), feedDto.getCreateTime(), feedDto.getModifyTime());
                feedResList.add(feedRes);
            }
            final int start = (int)pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), feedResList.size());
            final Page<FeedRes> feed = new PageImpl<>(feedResList.subList(start, end), pageable, feedResList.size());

            return feed;
        } catch(Exception e){
            System.out.println("feed is empty2");
            throw new PostNotFoundException();
        }
    }

    @Override
    public Page<FeedRes> findProfileFeed(Long memberUid, String memberId, Pageable pageable) {
        try{
            List<FeedDto> feedDtoPages = postRepository.findMyPostsByMemberId(memberId);
            ArrayList<FeedRes> feedResList = new ArrayList<>();
            for (int i = 0; i < feedDtoPages.size(); i++) {
                FeedDto feedDto = feedDtoPages.get(i);
                feedDto.setLiked(postLikeRepository.existsByPostUidAndMemberUid(feedDto.getUid(), memberUid));
                feedDto.setLikeCnt(postLikeRepository.countByPostUid(feedDto.getUid()));
                feedDto.setHasCommented(postCommentRepository.existsByPostUidAndMemberUid(feedDto.getUid(), memberUid));
                feedDto.setCommentCnt(postCommentRepository.countByPostUid(feedDto.getUid()));
                EventCard eventCard = eventRepository.findEventCardByEventUid(feedDto.getEventUid());
                FeedRes feedRes = new FeedRes(feedDto.getUid(), eventCard, feedDto.getMemberId(), feedDto.isLiked(), feedDto.getLikeCnt(), feedDto.isHasCommented(), feedDto.getCommentCnt(), feedDto.getTitle(), feedDto.getContent(), feedDto.getImgUrl(), feedDto.getProfileImgUrl(), feedDto.getCreateTime(), feedDto.getModifyTime());
                feedResList.add(feedRes);
            }
            final int start = (int)pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), feedResList.size());
            final Page<FeedRes> feed = new PageImpl<>(feedResList.subList(start, end), pageable, feedResList.size());

            return feed;
        } catch(Exception e){
            throw new FeedNotFoundException();
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
                .post(postRepository.findByUid(postUid).orElseThrow(PostNotFoundException::new))
                .member(memberRepository.findMemberByUid(memberUid))
                .content(content)
                .build();
    }

    PostRes toPostRes(Post post) {
        return PostRes.builder().build();
    }
}
