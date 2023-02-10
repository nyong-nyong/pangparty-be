package nyongnyong.pangparty.exception;

public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException() {
        super("유효하지 않은 토큰");
    }

    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
