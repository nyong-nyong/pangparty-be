package nyongnyong.pangparty.dto.member;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberProfileReq {

    @NotBlank
    private String id;
    private String name;
    private String imgUrl;
    private String introduction;

}
