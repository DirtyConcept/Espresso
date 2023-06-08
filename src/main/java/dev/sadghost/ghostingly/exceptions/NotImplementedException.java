package dev.sadghost.ghostingly.exceptions;

/**
 * The {@code NotImplementedException} is thrown to indicate that a particular feature or functionality
 * is not yet implemented or supported in the current context.
 * <p>
 * This exception extends the {@link RuntimeException} class, which makes it an unchecked exception. It can be
 * thrown at runtime without being explicitly declared in a method's throws clause or caught in a try-catch block.
 * <p>
 * It is typically used as a temporary placeholder to mark areas of code that require further development or to
 * communicate to the user that a specific feature is not yet available.
 *
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * public void someMethod() {
 *     throw new NotImplementedException("This feature is not yet implemented.");
 * }
 * }</pre>
 *
 * <p><b>Example Usage with Cause:</b></p>
 * <pre>{@code
 * public void someMethod() {
 *     try {
 *         // Code that causes the exception
 *     } catch (UnsupportedOperationException e) {
 *         throw new NotImplementedException("This feature is not yet implemented.", e);
 *     }
 * }
 * }</pre>
 *
 * @since 1.0.0
 * @author SadGhost
 */
public final class NotImplementedException extends RuntimeException {

    /**
     * Constructs a new {@code NotImplementedException} with no detail message.
     */
    public NotImplementedException() {
        super();
    }

    /**
     * Constructs a new {@code NotImplementedException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public NotImplementedException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code NotImplementedException} with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method).
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

