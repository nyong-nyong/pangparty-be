package nyongnyong.pangparty.dto.album;


import lombok.*;
import nyongnyong.pangparty.entity.album.AlbumMediaComment;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AlbumMediaCommentSimpleRes {

    private Long uid;
    private String memberId;
    private Long albumMediaUid;
    @NotEmpty(message = "내용은 필수입니다.")
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    @Builder
    public AlbumMediaCommentSimpleRes(Long uid, String memberId, Long albumMediaUid, String content, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.uid = uid;
        this.memberId = memberId;
        this.albumMediaUid = albumMediaUid;
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public AlbumMediaCommentSimpleRes(AlbumMediaComment albumMediaComment) {
        this.uid = albumMediaComment.getUid();
        this.memberId = albumMediaComment.getMember().getMemberProfile().getId();
        this.albumMediaUid = albumMediaComment.getAlbumMedia().getUid();
        this.content = albumMediaComment.getContent();
        this.createTime = albumMediaComment.getCreateTime();
        this.modifyTime = albumMediaComment.getModifyTime();
    }

}
