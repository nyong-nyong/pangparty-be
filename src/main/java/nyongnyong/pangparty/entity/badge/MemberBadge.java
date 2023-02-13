package nyongnyong.pangparty.entity.badge;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@ToString(of = {"uid", "acquireTime"})
public class MemberBadge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_uid")
    @ToString.Exclude
    private Badge badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime acquireTime;

    public MemberBadge(Badge badge, Member member) {
        this.badge = badge;
        this.member = member;
    }

    //    @Builder
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
