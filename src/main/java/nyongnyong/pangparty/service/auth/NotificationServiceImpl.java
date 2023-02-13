package nyongnyong.pangparty.service.auth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.NotificationRes;
import nyongnyong.pangparty.entity.event.Event;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.exception.EventNotFoundException;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.repository.event.EventRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void alertFollow(String followerId, String followeeId) {
        Member follower = memberRepository.findMemberById(followerId);
        Member followee = memberRepository.findMemberById(followeeId);
        if (follower == null || followee == null) {
            throw new MemberNotFoundException();
        }

        String type = "follow";
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        NotificationRes notificationRes = NotificationRes.builder()
                .type(type)
                .toId(followeeId)
                .fromId(followerId)
                .createTime(nowDate)
                .imgUrl(follower.getMemberProfile().getImgUrl())
                .isRead(false)
                .build();

        saveToDatabase(followee, notificationRes);
    }

    @Override
    @Transactional
    public void alertTargetEvent(Long eventUid, Long hostUid, String targetId) {
        Member host = memberRepository.findMemberByUid(hostUid);
        Member target = memberRepository.findMemberById(targetId);
        if (host == null || target == null) {
            throw new MemberNotFoundException();
        }

        Event event = eventRepository.findEventByUid(eventUid);
        if (event == null) {
            throw new EventNotFoundException();
        }

        String type = "targetEvent";
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        NotificationRes notificationRes = NotificationRes.builder()
                .type(type)
                .toId(targetId)
                .fromId(host.getMemberProfile().getId())
                .createTime(nowDate)
                .imgUrl(event.getImgUrl())
                .eventName(event.getEventName())
                .isRead(false)
                .build();

        saveToDatabase(target, notificationRes);
    }

    @Override
    @Transactional
    public void alertFollowerEvent(Long eventUid, Long hostUid, Long followerUid) {
        Member host = memberRepository.findMemberByUid(hostUid);
        Member follower = memberRepository.findMemberByUid(followerUid);
        if (host == null || follower == null) {
            throw new MemberNotFoundException();
        }

        Event event = eventRepository.findEventByUid(eventUid);
        if (event == null) {
            throw new EventNotFoundException();
        }

        String type = "followeeEvent";
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        NotificationRes notificationRes = NotificationRes.builder()
                .type(type)
                .toId(follower.getMemberProfile().getId())
                .fromId(host.getMemberProfile().getId())
                .createTime(nowDate)
                .imgUrl(event.getImgUrl())
                .eventName(event.getEventName())
                .isRead(false)
                .build();

        saveToDatabase(follower, notificationRes);
    }

    private void saveToDatabase(Member target, NotificationRes notificationRes) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("pangparty");

        DatabaseReference notiRef = ref.child(Long.toString(target.getUid())); // 알림 받을 사람
        DatabaseReference nextNotiRef = notiRef.push(); // 다음 키 값으로 푸시
        String postId = nextNotiRef.getKey(); // 현재 알람의1 키값을 가져옴 (덮어쓰기, 중복 방지)
        DatabaseReference saveNotification = notiRef.child(postId); // to의 아이디 값의 child node
        saveNotification.setValueAsync(notificationRes);
    }

    @Override
    public void readNotification(String memberId, String notificationId) {
        Member member = memberRepository.findMemberById(memberId);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("pangparty");
        DatabaseReference notiRef = ref.child(member.getUid().toString()).child(notificationId);
        notiRef.child("isRead").setValueAsync(true);
    }
}
