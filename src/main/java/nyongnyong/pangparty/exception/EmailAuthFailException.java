package nyongnyong.pangparty.exception;

public class EmailAuthFailException extends RuntimeException {

    public EmailAuthFailException() {
        super("이메일 인증 실패");
    }

    public EmailAuthFailException(String message) {
        super(message);
    }

    public EmailAuthFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
