package nyongnyong.pangparty.entity.feed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"uid", "content", "createTime"})
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_uid")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    private String content;
    private LocalDateTime createTime;

    public void changePost(Post post){
        this.post = post;
        if(!post.getPostComments().contains(this)){
            post.getPostComments().add(this);
        }
    }
}
