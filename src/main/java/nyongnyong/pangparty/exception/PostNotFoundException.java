package nyongnyong.pangparty.exception;

public class PostNotFoundException extends RuntimeException{
    
    public PostNotFoundException() {
        super("존재하지 않는 게시물");
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
