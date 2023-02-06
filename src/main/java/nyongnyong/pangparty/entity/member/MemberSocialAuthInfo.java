package nyongnyong.pangparty.entity.member;

import lombok.*;
import nyongnyong.pangparty.common.SocialAuthType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"memberUid", "socialType", "externalId", "updateTime"})
public class MemberSocialAuthInfo implements Serializable {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
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
