package nyongnyong.pangparty.service.auth;

public interface MailService {

    /**
     * 인증 코드 생성
     * @param size 인증 코드 길이
     * @return 인증 코드
     */
    String getKey(int size);

    /**
     * 이메일 전송
     * @param to 수신자 이메일
     * @param subject 제목
     * @param text 내용
     */
    void sendMail(String to, String subject, String text);
}
