package dev.sadghost.espresso.exceptions;

/**
 * The {@code NotImplementedException} class represents an exception that is thrown to indicate that a particular
 * feature or functionality is not implemented.
 * <p>
 * This class extends the {@link UnsupportedOperationException} class, which is a runtime exception indicating that
 * the requested operation is not supported.
 * <p>
 * This exception can be used to signal that a method or feature is not implemented and should not be called. It can
 * be thrown when a class or method is under development or when a certain functionality is intentionally omitted.
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * throw new NotImplementedException("This feature is not yet implemented");
 * }
 * </pre>
 *
 * @see UnsupportedOperationException
 */
public final class NotImplementedException extends UnsupportedOperationException {

    /**
     * Constructs a new {@code NotImplementedException} with no detail message.
     */
    public NotImplementedException() {
        super();
    }

    /**
     * Constructs a new {@code NotImplementedException} with the specified detail message.
     *
     * @param message the detail message describing the reason for the exception.
     */
    public NotImplementedException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code NotImplementedException} with the specified detail message and cause.
     *
     * @param message the detail message describing the reason for the exception.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code NotImplementedException} with the specified cause and a detail message
     * that is based on the cause.
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public NotImplementedException(Throwable cause) {
        super(cause);
    }
}