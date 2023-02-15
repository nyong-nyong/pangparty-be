package nyongnyong.pangparty.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
public class MemberProfile implements Serializable {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    @JsonIgnore
    private Member member;
    @Column(unique = true)
    private String id;
    private String name;
    private String imgUrl;
    private String introduction;
    @LastModifiedDate
    private LocalDateTime updateTime;

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