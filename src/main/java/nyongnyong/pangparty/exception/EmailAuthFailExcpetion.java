package nyongnyong.pangparty.exception;

public class EmailAuthFailExcpetion extends RuntimeException {

    public EmailAuthFailExcpetion() {
        super("이메일 인증 실패");
    }

    public EmailAuthFailExcpetion(String message) {
        super(message);
    }

    public EmailAuthFailExcpetion(String message, Throwable cause) {
        super(message, cause);
    }
}
