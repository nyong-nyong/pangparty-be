package nyongnyong.pangparty.entity.event;

import lombok.*;
import nyongnyong.pangparty.entity.member.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "content", "createTime", "startTime", "endTime"})
public class Banner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uid")
    private Member member;

    @Lob
    private String content;

    private LocalDateTime createTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public Banner(String content, LocalDateTime createTime, LocalDateTime startTime, LocalDateTime endTime) {
        this.content = content;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
