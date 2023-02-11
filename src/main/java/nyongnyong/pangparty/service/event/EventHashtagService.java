package nyongnyong.pangparty.service.event;

import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.hashtag.Hashtag;

public interface EventHashtagService {
    Long addEventHashtag(Event event, Hashtag hashtag);
}
