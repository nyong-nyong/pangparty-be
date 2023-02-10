package nyongnyong.pangparty.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@NoArgsConstructor
@ToString
public class FriendshipRes {

    @NotBlank
    private Long uid;

    @NotBlank
    private String id;

    @NotBlank
    private String imgUrl;

    boolean isFollowing;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipRes that = (FriendshipRes) o;
        return Objects.equals(uid, that.uid) && Objects.equals(id, that.id) && Objects.equals(imgUrl, that.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, id, imgUrl);
    }
}
