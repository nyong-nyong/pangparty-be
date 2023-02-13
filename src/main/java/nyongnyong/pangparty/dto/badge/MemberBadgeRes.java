package nyongnyong.pangparty.dto.badge;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MemberBadgeRes {

    @NotNull
    private Long uid;

    boolean isGet;
}
