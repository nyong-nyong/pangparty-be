package nyongnyong.pangparty.entity.feed;

import lombok.*;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "content", "createTime", "modifyTime", "hit"})
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @Lob
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private int hit;

    @Builder
    public Post(String content, LocalDateTime createTime, LocalDateTime modifyTime, int hit) {
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.hit = hit;
    }

    public void addPostComment(PostComment postComment) {
        this.postComments.add(postComment);
        if (postComment.getPost() != this) {
            postComment.changePost(this);
        }
    }

    public void addPostLike(PostLike postLike) {
        this.postLikes.add(postLike);
        if (postLike.getPost() != this) {
            postLike.changePost(this);
        }
    }
}
