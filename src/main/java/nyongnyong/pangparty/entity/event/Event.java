package nyongnyong.pangparty.entity.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.entity.album.Album;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "eventName", "introduction", "imgUrl", "dDay", "createTime", "modifyTime", "startTime", "endTime", "partyTime", "isPrivate"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne
    private Member host;
    private String eventName;
    private String introduction;
    private String imgUrl;
    private LocalDate dDay;

    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime partyTime;

    private boolean isPrivate;
    @OneToOne(mappedBy = "event")
    private RollingPaper rollingPaper;

    @OneToOne(mappedBy = "event")
    private Album album;
//    private boolean hasPlaylist;
//    private boolean hasFunding;

    public void changeRollingPaper(RollingPaper rollingPaper) {
        this.rollingPaper = rollingPaper;
        if (rollingPaper.getEvent() != this) {
            rollingPaper.changeEvent(this);
        }
    }
    public void changeAlbum(Album album) {
        this.album = album;
        if (album.getEvent() != this) {
            album.changeEvent(this);
        }
    }

}
