package dev.sadghost.espresso.base;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code Preconditions} class is a utility class used to ensure the conditions of arguments within the code.
 * It provides methods for validating arguments, such as checking for truthiness or non-nullity, and throws appropriate
 * exceptions if the conditions are not met.
 * <p>
 * The class follows a design pattern commonly known as "preconditions" or "assertions," which allows developers to
 * express their expectations about the values passed to a method or constructor.
 * <p>
 * By using the preconditions provided by this class, developers can enhance code reliability and catch potential
 * issues early in the development process.
 * <p>
 * This class is marked as final to prevent subclassing and promote the use of its static methods directly.
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public void processUser(User user) {
 *     Preconditions.checkNonNull(user, "User cannot be null");
 *     // Perform user processing logic
 * }
 * }</pre>
 *
 * @author SadGhost
 * @since 1.0.0
 */
public final class Preconditions {

    /**
     * Seals the class to prevent instantiation.
     */
    @Contract(pure = true)
    private Preconditions() {}

    /**
     * Ensures that the provided boolean value is true.
     * <p>
     * If the value is false, an {@link IllegalArgumentException} is thrown without a specific exception message.
     * <p>
     * This method is a convenience wrapper for {@link #checkArgument(boolean, String)}, where the message is not
     * explicitly provided.
     *
     * @param value a boolean value.
     * @throws IllegalArgumentException if the value is {@code false}.
     * @see #checkArgument(boolean, String)
     * @since 1.0.0
     */
    @Contract(value = "false -> fail", pure = true)
    public static void checkArgument(final boolean value) {
        checkArgument(value, null);
    }

    /**
     * Ensures that the provided boolean value is true.
     * <p>
     * If the value is false, an {@link IllegalArgumentException} is thrown with the specified exception message.
     * <p>
     * This method is useful for validating conditions that should be met before proceeding with further code execution.
     *
     * <h4>Usage Example:</h4>
     * <pre>{@code
     * public void processUser(User user) {
     *     Preconditions.checkArgument(user.isEnabled(), "User must be enabled");
     *     // Perform user processing logic
     * }
     * }</pre>
     *
     * @param value   the provided value to validate.
     * @param message the exception message to be included in the thrown exception if the condition is not met.
     * @throws IllegalArgumentException if the value is {@code false}.
     * @since 1.0.0
     */
    @Contract(value = "false, _ -> fail", pure = true)
    public static void checkArgument(final boolean value,
                                     final @Nullable String message) {
        if (!value) throw new IllegalArgumentException(message);
    }

    /**
     * Ensures that the provided argument is not null and returns the argument if it is not null.
     * <p>
     * If the argument is null, a {@link NullPointerException} is thrown without a specific exception message.
     * <p>
     * This method is a convenience wrapper for {@link #checkNonNull(Object, String)}, where the message is not
     * explicitly provided.
     *
     * @param value the provided value to validate.
     * @param <T> the type of argument to validate.
     * @return the provided argument if it is not null.
     * @throws NullPointerException if the value is {@code null}.
     * @see #checkNonNull(Object, String)
     * @since 1.0.0
     */
    @Contract(value = "null -> fail; !null -> param1", pure = true)
    public static <T> @NotNull T checkNonNull(final @Nullable T value) {
        return checkNonNull(value, null);
    }

    /**
     * Ensures that the provided argument is not null and returns the argument if it is not null.
     * <p>
     * If the argument is null, a {@link NullPointerException} is thrown with the specified exception message.
     * <p>
     * This method is useful for validating that required arguments are not null before using them in code.
     *
     * <h4>Usage Example:</h4>
     * <pre>{@code
     * public void processUser(User user) {
     *     Preconditions.checkNonNull(user, "User cannot be null");
     *     // Perform user processing logic
     * }
     * }</pre>
     *
     * @param value the provided value to validate.
     * @param message the exception message to be included in the thrown exception if the value is null.
     * @param <T> the type of argument to validate.
     * @return the provided argument if it is not null.
     * @throws NullPointerException if the value is {@code null}.
     * @since 1.0.0
     */
    @Contract(value = "null, _ -> fail; !null, _ -> param1", pure = true)
    public static <T> @NotNull T checkNonNull(final @Nullable T value,
                                              final @Nullable String message) {
        if (value == null) throw new NullPointerException(message);
        return value;
    }
}

