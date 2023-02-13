package nyongnyong.pangparty.service.auth;

public interface NotificationService {

    void alertFollow(String followerId, String followeeId);

    void alertTargetEvent(Long eventUid, Long hostUid, String targetId);

    void alertFollowerEvent(Long eventUid, Long hostUid, Long targetUid);

    void readNotification(String memberId, String notificationId);
}
