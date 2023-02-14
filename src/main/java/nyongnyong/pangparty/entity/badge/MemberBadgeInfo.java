package nyongnyong.pangparty.entity.badge;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberBadgeInfo implements Serializable {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    @Column(columnDefinition = "bigint default 0")
    private long loginCount;
    @Column(columnDefinition = "bigint default 0")
    private long participateCount;
    @Column(columnDefinition = "bigint default 0")
    private long receiveCount;

    @Builder
    public MemberBadgeInfo(Member member, long loginCount, long participateCount, long receiveCount) {
        this.member = member;
        this.loginCount = loginCount;
        this.participateCount = participateCount;
        this.receiveCount = receiveCount;
    }

}
