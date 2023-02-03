package nyongnyong.pangparty.entity.badge;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "badgeName", "imgUrl", "badgeCondition"})
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String badgeName;
    private String imgUrl;
    private String badgeCondition;

    public Badge(String badgeName, String imgUrl, String badgeCondition) {
        this.badgeName = badgeName;
        this.imgUrl = imgUrl;
        this.badgeCondition = badgeCondition;
    }

}
