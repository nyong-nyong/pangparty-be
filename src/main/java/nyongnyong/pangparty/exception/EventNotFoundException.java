package nyongnyong.pangparty.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("존재하지 않는 이벤트");
    }

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
