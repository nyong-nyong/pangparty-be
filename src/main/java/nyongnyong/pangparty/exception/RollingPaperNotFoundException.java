package nyongnyong.pangparty.exception;

public class RollingPaperNotFoundException extends RuntimeException {
    public RollingPaperNotFoundException() {
        super("존재하지 않는 롤링페이퍼");
    }

    public RollingPaperNotFoundException(String message) {
        super(message);
    }

    public RollingPaperNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
