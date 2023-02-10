package nyongnyong.pangparty.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private int size;

    public String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }

    private String getAuthCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int num = 0;

        while (sb.length() < size) {
            num = random.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }

    @Override
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}

