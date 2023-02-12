package nyongnyong.pangparty.entity.feed;

import lombok.*;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    @ToString.Exclude
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> postComments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> postLikes;

    private String title;
    @Lob
    private String content;

    private String imgUrl;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime modifyTime;

    @Column(columnDefinition = "int default 0", nullable = false)
    private int hit;

    @Builder
    public Post(Event event, Member member, String title, String content, String imgUrl) {
        this.event = event;
        this.member = member;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
    }

    //    @Builder
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
