package nyongnyong.pangparty.entity.hashtag;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "addTime"})
public class MemberHashtag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_uid")
    private Hashtag hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    private LocalDateTime addTime;

    @Builder
    public MemberHashtag(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public void changeHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }


    public void changeMember(Member member) {
        this.member = member;
    }
}
