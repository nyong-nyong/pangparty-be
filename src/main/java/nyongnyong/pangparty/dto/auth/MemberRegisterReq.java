package nyongnyong.pangparty.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ToString
public class MemberRegisterReq {
    @NotNull
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;
    // TODO add password restrictions @Size()
    private String password;
    // TODO add id restrictions @Size
    private String id;
    private String name;
    private String introduction;
    private String imgUrl; // profile image
}
