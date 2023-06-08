package dev.sadghost.ghostingly.base;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * An interface used to manage the messages in the plugin
 * in a more professional way.
 *
 * @author SadGhost
 * @since 1.0.0
 */
public interface IMessagesManager extends IReloadable {

    /**
     * Gets a message using the message's {@code key} and returns
     * a component containing the message.
     *
     * @param key The message key.
     * @return The message {@code Component}
     * @since 1.0.0
     */
    @NotNull Component getMessage(@NotNull String key);

    /**
     * Gets an error message that'll be used to inform that some
     * issue occurred.
     * </p>
     * Allows to put an error code in the error message to give more
     * information to the developers & staff about the issues.
     *
     * @param code The error code used for identification.
     * @return The formatted error message.
     * @since 1.0.0
     */
    @NotNull Component getErrorMessage(@NotNull String code);
}