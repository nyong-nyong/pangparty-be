package nyongnyong.pangparty.entity.rollingpaper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.event.Event;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "bgColor"})
public class RollingPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    private Event event;

    @OneToMany(mappedBy = "rollingPaper")
    private List<RollingPaperPiece> rollingPaperPieces;

    @OneToMany(mappedBy = "rollingPaper")
    private List<RollingPaperSticker> rollingPaperStickers;

    private String bgColor;

    public void addRollingPaperPiece(RollingPaperPiece rollingPaperPiece) {
        this.rollingPaperPieces.add(rollingPaperPiece);
        if (rollingPaperPiece.getRollingPaper() != this) {
            rollingPaperPiece.changeRollingPaper(this);
        }
    }
    public void addRollingPaperSticker(RollingPaperSticker rollingPaperSticker) {
        this.rollingPaperStickers.add(rollingPaperSticker);
        if (rollingPaperSticker.getRollingPaper() != this) {
            rollingPaperSticker.changeRollingPaper(this);
        }
    }
    public void changeEvent(Event event) {
        this.event = event;
        if (event.getRollingPaper() != this) {
            event.changeRollingPaper(this);
        }
    }

}
