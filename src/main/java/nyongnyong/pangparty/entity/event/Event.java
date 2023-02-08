package nyongnyong.pangparty.entity.event;

import lombok.*;
import nyongnyong.pangparty.entity.album.Album;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "eventName", "introduction", "imgUrl", "dDay", "createTime", "modifyTime", "startTime", "endTime", "partyTime", "isPrivate"})
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne
    private Member host;
    private String eventName;
    private String introduction;
    private String imgUrl;
    private LocalDate dDay;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime modifyTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime partyTime;

    private boolean isPrivate;
    @OneToOne(mappedBy = "event")
    private RollingPaper rollingPaper;

    @OneToOne(mappedBy = "event")
    private Album album;

    @OneToOne(mappedBy = "event")
    private EventTarget eventTarget;

//    private boolean hasPlaylist;
//    private boolean hasFunding;

    @Builder
    public Event(String eventName, String introduction, String imgUrl, LocalDate dDay, LocalDateTime createTime, LocalDateTime modifyTime, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime partyTime, boolean isPrivate) {
        this.eventName = eventName;
        this.introduction = introduction;
        this.imgUrl = imgUrl;
        this.dDay = dDay;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.partyTime = partyTime;
        this.isPrivate = isPrivate;
    }

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
