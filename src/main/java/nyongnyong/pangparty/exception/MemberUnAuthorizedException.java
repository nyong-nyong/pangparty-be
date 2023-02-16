package nyongnyong.pangparty.exception;

public class MemberUnAuthorizedException extends RuntimeException {

        public MemberUnAuthorizedException() {
            super("인증하지 않은 유저");
        }

        public MemberUnAuthorizedException(String message) {
            super(message);
        }

        public MemberUnAuthorizedException(String message, Throwable cause) {
            super(message, cause);
        }
}
