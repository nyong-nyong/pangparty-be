package nyongnyong.pangparty.dto.badge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeRes {

    @NotNull
    private Long uid;

    @NotBlank
    private String badgeName;

    @NotBlank
    private String trueImgUrl;

    @NotBlank
    private String falseImgUrl;

    @NotBlank
    private String badgeCondition;
}
