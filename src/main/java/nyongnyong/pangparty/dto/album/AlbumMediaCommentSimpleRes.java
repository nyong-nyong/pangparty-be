package nyongnyong.pangparty.dto.album;


import lombok.*;
import nyongnyong.pangparty.entity.album.AlbumMediaComment;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AlbumMediaCommentSimpleRes {

    private Long uid;
    private Long eventUid;
    private Long memberUid;
    private Long albumMediaUid;
    @NotEmpty(message = "내용은 필수입니다.")
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    @Builder
    public AlbumMediaCommentSimpleRes(Long uid, Long eventUid, Long memberUid, Long albumMediaUid, String content, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.uid = uid;
        this.eventUid = eventUid;
        this.memberUid = memberUid;
        this.albumMediaUid = albumMediaUid;
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public AlbumMediaCommentSimpleRes(AlbumMediaComment albumMediaComment) {
        this.uid = albumMediaComment.getUid();
        this.eventUid = albumMediaComment.getAlbumMedia().getAlbum().getEvent().getUid();
        this.memberUid = albumMediaComment.getMember().getUid();
        this.albumMediaUid = albumMediaComment.getAlbumMedia().getUid();
        this.content = albumMediaComment.getContent();
        this.createTime = albumMediaComment.getCreateTime();
        this.modifyTime = albumMediaComment.getModifyTime();
    }

}
