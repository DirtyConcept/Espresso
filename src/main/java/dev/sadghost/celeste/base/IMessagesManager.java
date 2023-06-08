package dev.sadghost.celeste.base;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code IMessagesManager} interface is used to manage the messages in the plugin
 * in a more professional and organized way. It extends the {@link IReloadable} interface,
 * indicating that message managers can also be reloaded.
 * <p>
 * Message managers are responsible for retrieving and formatting messages to be displayed
 * in the plugin, providing a centralized location for message handling and localization.
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public class MessagesManager implements IMessagesManager {
 *     // Implementation of other methods
 *
 *     {@literal @}Override
 *     public void reload() {
 *         // Reload message configuration
 *         // Update message cache
 *     }
 * }
 * }</pre>
 *
 * @see IReloadable
 * @since 1.0.0
 * @author SadGhost
 */
public interface IMessagesManager extends IReloadable {

    /**
     * Retrieves a message using the specified {@code key} and returns
     * a {@link Component} containing the formatted message.
     *
     * @param key The key associated with the message.
     * @return The formatted message {@link Component}.
     * @since 1.0.0
     */
    @NotNull Component getMessage(@NotNull String key);

    /**
     * Retrieves an error message to inform that an issue has occurred.
     * <p>
     * This method allows an error code to be included in the error message,
     * providing additional information to developers and staff about the issue.
     *
     * @param code The error code used for identification.
     * @return The formatted error message {@link Component}.
     * @since 1.0.0
     */
    @NotNull Component getErrorMessage(@NotNull String code);
}