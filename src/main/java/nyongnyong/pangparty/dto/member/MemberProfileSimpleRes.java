package nyongnyong.pangparty.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import nyongnyong.pangparty.entity.member.MemberProfile;

@Data
@Builder
@AllArgsConstructor
public class MemberProfileSimpleRes {

    private String Id;
    private String name;
    private String imgUrl;
    private String introduction;
    private boolean isFollowed;

    public MemberProfileSimpleRes(MemberProfile memberProfile){
        this.Id = memberProfile.getId();
        this.name = memberProfile.getName();
        this.imgUrl = memberProfile.getImgUrl();
        this.introduction = memberProfile.getIntroduction();
        this.isFollowed = false;
    }

}
