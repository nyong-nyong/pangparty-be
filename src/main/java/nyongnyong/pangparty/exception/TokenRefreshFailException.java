package nyongnyong.pangparty.exception;

public class TokenRefreshFailException extends RuntimeException {

    public TokenRefreshFailException() {
        super("토큰 갱신 실패");
    }

    public TokenRefreshFailException(String message) {
        super(message);
    }

    public TokenRefreshFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
