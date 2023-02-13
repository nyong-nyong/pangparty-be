package nyongnyong.pangparty.exception;

public class FeedNotFoundException extends RuntimeException{

    public FeedNotFoundException() {
        super("사용자가 작성한 게시글이 없습니다.");
    }

    public FeedNotFoundException(String message) {
        super(message);
    }

    public FeedNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
