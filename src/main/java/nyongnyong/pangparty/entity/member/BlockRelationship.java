package nyongnyong.pangparty.entity.member;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"uid", "blockTime"})
public class BlockRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne
    @JoinColumn(name = "blocker_uid")
    private Member blocker;
    @ManyToOne
    @JoinColumn(name = "blocked_uid")
    private Member blocked;
    private LocalDateTime blockTime;

    @Builder
    public BlockRelationship(LocalDateTime blockTime) {
        this.blockTime = blockTime;
    }

}
