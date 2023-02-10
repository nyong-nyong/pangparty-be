package nyongnyong.pangparty.exception;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() {
        super("토큰 만료");
    }

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
