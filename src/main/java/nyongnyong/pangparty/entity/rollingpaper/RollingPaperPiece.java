package nyongnyong.pangparty.entity.rollingpaper;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "writerName", "createTime", "modifyTime", "content", "bgColor", "bgImgUrl", "bgImgAlt", "fontFamily", "textColor", "textAlign"})
public class RollingPaperPiece implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolling_paper_uid")
    private RollingPaper rollingPaper;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    private String writerName;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    @Lob
    private String content;
    private String bgColor;
    private String bgImgUrl;
    private String bgImgAlt;
    private String fontFamily;
    private String textColor;
    private String textAlign;

    @Builder
    public RollingPaperPiece(String writerName, LocalDateTime createTime, LocalDateTime modifyTime, String content, String bgColor, String bgImgUrl, String bgImgAlt, String fontFamily, String textColor, String textAlign) {
        this.writerName = writerName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.content = content;
        this.bgColor = bgColor;
        this.bgImgUrl = bgImgUrl;
        this.bgImgAlt = bgImgAlt;
        this.fontFamily = fontFamily;
        this.textColor = textColor;
        this.textAlign = textAlign;
    }

    public void changeRollingPaper(RollingPaper rollingPaper) {
        this.rollingPaper = rollingPaper;
        if (!rollingPaper.getRollingPaperPieces().contains(this)) {
            rollingPaper.getRollingPaperPieces().add(this);
        }
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
