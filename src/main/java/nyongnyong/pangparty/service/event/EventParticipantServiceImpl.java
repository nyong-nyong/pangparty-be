package nyongnyong.pangparty.service.event;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.entity.event.EventParticipant;
import nyongnyong.pangparty.repository.event.EventParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventParticipantServiceImpl implements EventParticipantService {

    private final EventParticipantRepository eventParticipantRepository;

    @Override
    public EventParticipant findEventParticipantByMemberUidAndEventUid(Long memberUid, Long eventUid) {
        return eventParticipantRepository.findByMemberUidAndEventUid(memberUid, eventUid);
    }

}
