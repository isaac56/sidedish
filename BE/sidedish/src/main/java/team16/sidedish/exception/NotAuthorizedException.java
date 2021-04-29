package team16.sidedish.exception;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super("권한이 없습니다.");
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
