package dev.sadghost.ghostingly.base;

/**
 * Refers an instance can be reloaded, and adds
 * the methods for reloading.
 *
 * @author SadGhost
 * @since 1.0.0
 */
public interface IReloadable {

    /**
     * Reloads the instance's data, actions etc... Used for reloading the plugin.
     *
     * @since 1.0.0
     */
    void reload();
}
