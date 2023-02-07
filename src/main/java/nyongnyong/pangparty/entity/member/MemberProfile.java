package nyongnyong.pangparty.entity.member;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"memberUid", "id", "name","imgUrl", "introduction", "updateTime"})
public class MemberProfile implements Serializable {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    private Member member;
    @Column(unique = true)
    private String id;
    private String name;
    private String imgUrl;
    private String introduction;
    private LocalDateTime updateTime;

//    @Builder
//    public MemberProfile(String id, String name, String imgUrl, String introduction, LocalDateTime updateTime) {
//        this.id = id;
//        this.name = name;
//        this.imgUrl = imgUrl;
//        this.introduction = introduction;
//        this.updateTime = updateTime;
//    }
    @Builder
    public MemberProfile(Member member, String id, String name, String imgUrl, String introduction, LocalDateTime updateTime) {
        this.member = member;
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.introduction = introduction;
        this.updateTime = updateTime;
    }
}