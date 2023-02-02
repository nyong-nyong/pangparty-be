package nyongnyong.pangparty.entity.rollingpaper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "createTime", "locX", "locY", "zIndex", "angle", "scale"})
public class RollingPaperSticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolling_paper_uid")
    private RollingPaper rollingPaper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    @OneToOne
    @JoinColumn(name = "sticker_uid")
    private Sticker sticker;

    private LocalDateTime createTime;
    private String locX;
    private String locY;
    private String zIndex;
    private String angle;
    private String scale;

    public void changeRollingPaper(RollingPaper rollingPaper) {
        this.rollingPaper = rollingPaper;
        if (!rollingPaper.getRollingPaperStickers().contains(this)) {
            rollingPaper.getRollingPaperStickers().add(this);
        }
    }

    public void changeMember(Member member) {
        this.member = member;
    }

    public void changeSticker(Sticker sticker) {
        this.sticker = sticker;
    }

}
