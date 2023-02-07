package nyongnyong.pangparty.entity.rollingpaper;

import lombok.*;
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
@ToString
public class RollingPaperSticker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolling_paper_uid")
    @ToString.Exclude
    private RollingPaper rollingPaper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    @OneToOne
    @JoinColumn(name = "sticker_uid")
    private Sticker sticker;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;
    private int leftLoc;
    private int topLoc;
    private String zIndex;
    private float angle;
    private float scale;

    @Builder
    public RollingPaperSticker(RollingPaper rollingPaper, Member member, Sticker sticker, int leftLoc, int topLoc, String zIndex, float angle, float scale) {
        this.rollingPaper = rollingPaper;
        this.member = member;
        this.sticker = sticker;
        this.leftLoc = leftLoc;
        this.topLoc = topLoc;
        this.zIndex = zIndex;
        this.angle = angle;
        this.scale = scale;
    }

    public RollingPaperSticker(LocalDateTime createTime, int leftLoc, int topLoc, String zIndex, float angle, float scale) {
        this.createTime = createTime;
        this.leftLoc = leftLoc;
        this.topLoc = topLoc;
        this.zIndex = zIndex;
        this.angle = angle;
        this.scale = scale;
    }

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
