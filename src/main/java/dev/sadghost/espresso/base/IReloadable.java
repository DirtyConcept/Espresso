package dev.sadghost.espresso.base;

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
 *     @Override
 *     public void reload() {
 *         // Perform plugin-specific reloading logic
 *     }
 * }
 *
 * public class Configuration implements IReloadable {
 *     // Implementation of other methods
 *
 *     @Override
 *     public void reload() {
 *         // Perform configuration-specific reloading logic
 *     }
 * }
 * }</pre>
 *
 * <p>
 * In the above example, both the {@code Plugin} and {@code Configuration} classes implement the
 * {@code IReloadable} interface. This allows instances of these classes to be reloaded using a
 * common interface method, {@code reload()}, which can be called on any object that implements
 * the interface.
 *
 * <p>
 * Here's an example of how to use the {@code IReloadable} interface with instances of these classes:
 *
 * <pre>{@code
 * public class PluginCommand implements CommandExecutor {
 *     @Override
 *     public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
 *         if (args[0].equalsIgnoreCase("reload")) {
 *             IReloadable plugin = ...; // The plugin instance
 *             IReloadable configuration = ...; // The configuration instance
 *             plugin.reload(); // Reload the plugin
 *             configuration.reload(); // Reload the configuration
 *             // Other actions when reloading
 *             return true;
 *         }
 *
 *         // Other command logic
 *         return false;
 *     }
 * }
 * }</pre>
 *
 * <p>
 * In this example, we create instances of the {@code Plugin} and {@code Configuration} classes and
 * assign them to variables of type {@code IReloadable}. Since both classes implement the
 * {@code IReloadable} interface, we can call the {@code reload()} method on these instances to
 * perform the reloading logic specific to each class.
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
     *     @Override
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

