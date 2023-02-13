package nyongnyong.pangparty.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRes {

    private String type;
    private String toId;
    private String fromId;
    private String imgUrl;
    private String createTime;
    private String eventName;
    private boolean isRead;
    private String nextUrl;

    @Builder
    public NotificationRes(String type, String toId, String fromId, String imgUrl, String createTime, String eventName, boolean isRead) {
        this.type = type;
        this.toId = toId;
        this.fromId = fromId;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.eventName = eventName;
        this.isRead = isRead;
    }
}
