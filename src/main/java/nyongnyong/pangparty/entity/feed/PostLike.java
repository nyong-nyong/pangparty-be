package nyongnyong.pangparty.entity.feed;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostLike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_uid")
    @ToString.Exclude
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime likeTime;


    @Builder
    public PostLike(Post post, Member member) {
        this.post = post;
        this.member = member;
    }

    //    @Builder
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
