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

//    @NotBlank
//    private Long uid;

    @NotBlank
    private String id;

    private String name;
    @NotBlank
    private String imgUrl;

    boolean isFollowing;

    public FriendshipRes(String id, String name, String imgUrl) {
//        this.uid = uid;
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipRes that = (FriendshipRes) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
