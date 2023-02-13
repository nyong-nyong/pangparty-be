package nyongnyong.pangparty.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
public class MemberProfilePictureSimpleRes {

    private String memberId;
    @URL(message = "URL 형식이 아닙니다.")
    private String thumbnailUrl;
    private String extension;

    @Builder
    public MemberProfilePictureSimpleRes(String memberId, String thumbnailUrl) {
        this.memberId = memberId;
        this.thumbnailUrl = thumbnailUrl;
    }

}
