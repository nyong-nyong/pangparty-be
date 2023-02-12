package nyongnyong.pangparty.dto.feed;

import lombok.*;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostReq {

//    private String id; // 작성자 id
    private String title;
    private String content;
    private String imgUrl;
    private Long eventUid; // 이벤트 연동

    @Builder
    public PostReq(String title, String content, String imgUrl, Long eventUid) {
//        this.id = id;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.eventUid = eventUid;
    }
}
