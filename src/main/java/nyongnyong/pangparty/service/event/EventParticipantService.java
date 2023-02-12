package nyongnyong.pangparty.service.event;

import nyongnyong.pangparty.entity.event.EventParticipant;

public interface EventParticipantService {

    EventParticipant findEventParticipantByMemberUidAndEventUid(Long memberUid, Long eventUid);

    /**
     * 이벤트 참여자 삭제
     * @param memberUid
     * @param eventUid
     */
    void deleteEventParticipant(Long memberUid, Long eventUid);

    /**
     * 이벤트 참여자인지 확인: CountAlbumMediaAndRollingPaperPieceAndStickerByMemberUidAndEventUid
     * @param memberUid
     * @param eventUid
     * @return
     */
    boolean isEventParticipant(Long memberUid, Long eventUid);
}
