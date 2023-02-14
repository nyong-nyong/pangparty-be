package nyongnyong.pangparty.dto.event;

import lombok.Builder;

public class EventHeaderRes {
    private Long eventUid;
    private String imgUrl;

    @Builder
    public EventHeaderRes(Long eventUid, String imgUrl) {
        this.eventUid = eventUid;
        this.imgUrl = imgUrl;
    }
}
