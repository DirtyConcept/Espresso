package dev.sadghost.ghostingly.exceptions;

public final class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super();
    }

    public NotImplementedException(String message) {
        super(message);
    }
    
    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotImplementedException(Throwable cause) {
        super(cause);
    }
}
