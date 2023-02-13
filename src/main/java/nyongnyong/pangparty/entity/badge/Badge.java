package nyongnyong.pangparty.entity.badge;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Badge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String badgeName;
    private String trueImgUrl;
    private String falseImgUrl;
    private String badgeCondition;

    @Builder
    public Badge(Long uid, String badgeName, String trueImgUrl, String falseImgUrl, String badgeCondition) {
        this.uid = uid;
        this.badgeName = badgeName;
        this.trueImgUrl = trueImgUrl;
        this.falseImgUrl = falseImgUrl;
        this.badgeCondition = badgeCondition;
    }

    //    @Builder
    public Badge(String badgeName, String imgUrl, String badgeCondition) {
        this.badgeName = badgeName;
        this.trueImgUrl = imgUrl;
        this.badgeCondition = badgeCondition;
    }

}
