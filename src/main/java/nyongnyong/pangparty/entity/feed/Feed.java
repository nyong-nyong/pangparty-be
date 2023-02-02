package nyongnyong.pangparty.entity.feed;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nyongnyong.pangparty.common.FeedType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "memberUid", "feedType", "createTime"})
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @OneToOne
    @JoinColumn()
    private FeedActivity feedActivity;

    @OneToOne
    @JoinColumn()
    private Post post;
    private Long memberUid;
    private FeedType feedType;
    private LocalDateTime createTime;

}
