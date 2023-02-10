package nyongnyong.pangparty.entity.rollingpaper;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import nyongnyong.pangparty.entity.event.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class RollingPaper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_uid")
    @ToString.Exclude
    private Event event;

    @JsonIgnore
    @OneToMany(mappedBy = "rollingPaper")
    private List<RollingPaperPiece> rollingPaperPieces;

    @JsonIgnore
    @OneToMany(mappedBy = "rollingPaper")
    private List<RollingPaperSticker> rollingPaperStickers;

    private String bgColor;

    @Builder
    public RollingPaper(String bgColor) {
        this.bgColor = bgColor;
    }

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
    }

}
