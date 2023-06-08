package dev.sadghost.ghostingly.base;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A Utility class that's used to ensure the conditions of arguments
 * within the code.
 *
 * @author SadGhost
 * @since 1.0.0
 */
public final class Preconditions {

    /**
     * Seals the class.
     */
    @Contract(pure = true)
    private Preconditions() {}

    /**
     * Ensures that the argument provided is true.
     *
     * @param value a boolean value.
     * @throws IllegalArgumentException if the value is {@code false}
     * @see #checkArgument(boolean, String)
     * @since 1.0.0
     */
    @Contract(value = "false -> fail", pure = true)
    public static void checkArgument(final boolean value) {
        checkArgument(value, null);
    }

    /**
     * Ensures that the argument provided is true.
     *
     * @param value the provided value to validate.
     * @param message the exception message.
     * @throws IllegalArgumentException if the value is {@code false}
     * @since 1.0.0
     */
    @Contract(value = "false, _ -> fail", pure = true)
    public static void checkArgument(final boolean value,
                                     final @Nullable String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures that the argument provided is not null and
     * returns the provided argument.
     *
     * @param value the provided value to validate.
     * @param <T> the type of argument to validate.
     * @return the provided argument if not null.
     * @throws NullPointerException if the value is {@code null}
     * @see #checkArgument(boolean, String)
     * @since 1.0.0
     */
    @Contract(value = "null -> fail; !null -> param1", pure = true)
    public static <T> @NotNull T checkNonNull(final @Nullable T value) {
        return checkNonNull(value, null);
    }

    /**
     * Ensures that the argument provided is not null and
     * returns the provided argument.
     *
     * @param value the provided value to validate.
     * @param message the exception message.
     * @param <T> the type of argument to validate.
     * @return the provided argument if not null.
     * @throws NullPointerException if the value is {@code null}
     * @since 1.0.0
     */
    @Contract(value = "null, _ -> fail; !null, _ -> param1", pure = true)
    public static <T> @NotNull T checkNonNull(final @Nullable T value,
                                              final @Nullable String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }
}
