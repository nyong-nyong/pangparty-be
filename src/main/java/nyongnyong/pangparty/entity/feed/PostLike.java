package nyongnyong.pangparty.entity.feed;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "likeTime"})
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_uid")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    private LocalDateTime likeTime;

    @Builder
    public PostLike(LocalDateTime likeTime) {
        this.likeTime = likeTime;
    }

    public void changePost(Post post) {
        this.post = post;
        if (!post.getPostLikes().contains(this)) {
            post.getPostLikes().add(this);
        }
    }
}
