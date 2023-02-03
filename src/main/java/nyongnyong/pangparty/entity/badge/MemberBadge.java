package nyongnyong.pangparty.entity.badge;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "acquireTime"})
public class MemberBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_uid")
    private Badge badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    private LocalDateTime acquireTime;

    @Builder
    public MemberBadge(LocalDateTime acquireTime) {
        this.acquireTime = acquireTime;
    }

    public void changeBadge(Badge badge) {
        this.badge = badge;
    }

    public void changeMember(Member member) {
        this.member = member;
    }

}
