package nyongnyong.pangparty.entity.feed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"uid", "eventUid", "content", "createTime", "modifyTime", "hit"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private Long eventUid;

    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @OneToOne @MapsId
    @JoinColumn()
    private Member member;

    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private int hit;

    public void addPostComment(PostComment postComment){
        this.postComments.add(postComment);
        if(postComment.getPost() != this){
            postComment.changePost(this);
        }
    }

    public void addPostLike(PostLike postLike){
        this.postLikes.add(postLike);
        if(postLike.getPost() != this){
            postLike.changePost(this);
        }
    }
}
