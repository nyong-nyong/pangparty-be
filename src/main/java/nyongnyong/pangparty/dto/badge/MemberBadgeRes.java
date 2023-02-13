package nyongnyong.pangparty.dto.badge;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberBadgeRes {
    @NotNull
    private Long uid;

    @NotBlank
    private String badgeName;

    @NotBlank
    private String trueImgUrl;

    @NotBlank
    private String falseImgUrl;

    @NotBlank
    private String badgeCondition;

    private LocalDateTime acquireTime;
    boolean hasBadge;

    @Builder
    public MemberBadgeRes(Long badgeUid, String badgeName, String trueImgUrl, String falseImgUrl, String badgeCondition, LocalDateTime acquireTime) {
        this.uid = badgeUid;
        this.badgeName = badgeName;
        this.trueImgUrl = trueImgUrl;
        this.falseImgUrl = falseImgUrl;
        this.badgeCondition = badgeCondition;
        this.acquireTime = acquireTime;
    }
}
