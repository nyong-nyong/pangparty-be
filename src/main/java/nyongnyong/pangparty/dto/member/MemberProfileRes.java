package nyongnyong.pangparty.dto.member;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberProfileRes {

    @NotBlank
    private String id;

    @NotBlank
    private String email;

    private String name;
    private String imgUrl;

    private String introduction;

    private boolean isFollowing;
    @NotNull
    private Long followingCount;
    @NotNull
    private Long followerCount;

    @NotNull
    private Long hostEventCount;
    @NotNull
    private Long involvingEventCount;
    @NotNull
    private Long involvedEventCount;

}
