package nyongnyong.pangparty.entity.rollingpaper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RollingPaperPiece implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolling_paper_uid")
    @ToString.Exclude
    private RollingPaper rollingPaper;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;

    private String writerName;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
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
    public RollingPaperPiece(RollingPaper rollingPaper, Member member, String writerName, String content, String bgColor, String bgImgUrl, String bgImgAlt, String fontFamily, String textColor, String textAlign) {
        this.rollingPaper = rollingPaper;
        this.member = member;
        this.writerName = writerName;
        this.content = content;
        this.bgColor = bgColor;
        this.bgImgUrl = bgImgUrl;
        this.bgImgAlt = bgImgAlt;
        this.fontFamily = fontFamily;
        this.textColor = textColor;
        this.textAlign = textAlign;
    }

    //    @Builder
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
