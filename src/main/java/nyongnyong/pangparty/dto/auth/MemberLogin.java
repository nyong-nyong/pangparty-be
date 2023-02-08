package nyongnyong.pangparty.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MemberLogin {

    private String email;
    private String password;
}
