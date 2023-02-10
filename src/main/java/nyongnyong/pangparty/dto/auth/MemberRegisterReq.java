package nyongnyong.pangparty.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ToString
public class MemberRegisterReq {
    @NotNull
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @Size(min = 8, max = 20)
    private String password;
    @Size(min = 2, max = 15)
    private String id;
    private String name;
    private String introduction;
    private String imgUrl; // profile image
}
