package dev.sadghost.ghostingly.base;

/**
 * The {@code IReloadable} interface represents an instance that can be reloaded,
 * providing methods for reloading its data or performing specific actions.
 * <p>
 * This interface is typically used in scenarios where a plugin or module needs to support
 * reloading its configuration or state without restarting the entire application.
 * <p>
 * By implementing this interface, classes indicate that they have the ability to reload their
 * internal state or perform any necessary actions to update themselves based on external changes.
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public class Plugin implements IReloadable {
 *     // Implementation of other methods
 *
 *     {@literal @}Override
 *     public void reload() {
 *         // Perform plugin-specific reloading logic
 *     }
 * }
 * }</pre>
 *
 * @author SadGhost
 * @since 1.0.0
 */
public interface IReloadable {

    /**
     * Reloads the instance's data, actions, or any other relevant components.
     * <p>
     * Implementations of this method should define the necessary logic to reload the instance,
     * such as reloading configuration files, refreshing internal caches, or reinitializing
     * dependent components.
     *
     * <h4>Usage Example:</h4>
     * <pre>{@code
     * public class Plugin implements IReloadable {
     *     {@literal @}Override
     *     public void reload() {
     *         // Reload plugin configuration
     *         // Refresh internal caches
     *         // Reinitialize dependent components
     *     }
     * }
     * }</pre>
     *
     * @since 1.0.0
     */
    void reload();
}

