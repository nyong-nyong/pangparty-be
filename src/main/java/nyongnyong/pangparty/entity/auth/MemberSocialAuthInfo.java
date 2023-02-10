package nyongnyong.pangparty.entity.auth;

import lombok.*;
import nyongnyong.pangparty.common.SocialAuthType;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberSocialAuthInfo implements Serializable {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    @Enumerated(EnumType.STRING)
    private SocialAuthType socialType;
    private String externalId;
    private LocalDateTime updateTime;

    @Builder
    public MemberSocialAuthInfo(Member member, SocialAuthType socialType, String externalId, LocalDateTime updateTime) {
        this.member = member;
        this.socialType = socialType;
        this.externalId = externalId;
        this.updateTime = updateTime;
    }
}
