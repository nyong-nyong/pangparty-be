package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventHashtagServiceImpl implements EventHashtagService {

    @Override
    public Long addEventHashtag(Long eventUid, String hashtagName) {
        return null;
    }
}
