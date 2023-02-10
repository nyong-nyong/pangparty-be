package nyongnyong.pangparty.service.event;

import nyongnyong.pangparty.entity.event.EventParticipant;

public interface EventParticipantService {

    EventParticipant findEventParticipantByMemberUidAndEventUid(Long memberUid, Long eventUid);
}
