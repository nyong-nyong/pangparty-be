package nyongnyong.pangparty.exception;

public class StickerNotFoundException extends RuntimeException {
    public StickerNotFoundException() {
        super("유효하지 않은 스티커");
    }

    public StickerNotFoundException(String message) {
        super(message);
    }

    public StickerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
