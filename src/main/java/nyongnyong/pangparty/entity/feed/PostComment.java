package nyongnyong.pangparty.entity.feed;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "content", "createTime"})
public class PostComment implements Serializable {

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

    @Builder
    public PostComment(String content, LocalDateTime createTime) {
        this.content = content;
        this.createTime = createTime;
    }

    public void changePost(Post post) {
        this.post = post;
        if (!post.getPostComments().contains(this)) {
            post.getPostComments().add(this);
        }
    }
}
