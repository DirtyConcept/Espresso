package dev.sadghost.espresso.paper;

import dev.sadghost.espresso.base.Preconditions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A debugger class that facilitates the creation of a consistent plugin debugger instance with channels and message formatting.
 *
 * <p>
 * This class allows developers to create a debugger instance for their plugin, manage debug channels, and send debug messages to registered players.
 * It provides flexibility in defining the debug message format and supports multiple debug channels for organizing debugging information.
 * </p>
 *
 * <p>
 * This class is designed to be used internally within the application and is not intended for direct public use.
 * </p>
 *
 * <p>
 * Thread Safety: This class is not thread-safe as the underlying map of channels is not synchronized.
 * If multiple threads access the same Debugger instance concurrently and modify the channels map,
 * external synchronization must be applied to ensure thread safety.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * Plugin plugin = // get your plugin instance
 * Debugger debugger = Debugger.create(plugin);
 * debugger.register("channel1", "Channel 1");
 * debugger.subscribe("channel1", player1);
 * debugger.sendDebug("channel1", "Debug message");
 * }</pre>
 * </p>
 *
 * @since 1.0.0
 * @author SadGhost
 */
@ApiStatus.Experimental
public final class Debugger {
    @NotNull private final Map<String, DebugChannel> channels;
    /**
     * The default debug message format.
     *
     * @since 1.0.0
     */
    @NotNull private static Component debugMessage = Component.text("[DEBUG:%plugin%] [%channel%] %message%");

    /**
     * Constructs a Debugger instance using the specified plugin and the default debug message format.
     *
     * @param plugin the plugin using the debugger.
     */
    private Debugger(final @NotNull Plugin plugin) {
        this(plugin, debugMessage);
    }

    /**
     * Constructs a Debugger instance using the specified plugin and debug message format.
     *
     * @param plugin      the plugin using the debugger.
     * @param debugFormat the debug message format.
     */
    private Debugger(final @NotNull Plugin plugin, final @NotNull Component debugFormat) {
        channels = new HashMap<>();
        init(plugin, debugFormat);
    }

    /**
     * Creates a new Debugger instance using the provided plugin.
     *
     * @param plugin the plugin using the debugger.
     * @return the debugger instance.
     * @since 1.0.0
     */
    @Contract("_ -> new")
    public static @NotNull Debugger create(final @NotNull Plugin plugin) {
        return new Debugger(plugin);
    }

    /**
     * Creates a new Debugger instance using the provided plugin and debug message format.
     *
     * @param plugin      the plugin using the debugger.
     * @param debugFormat the debug message format.
     * @return the debugger instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> new")
    public static @NotNull Debugger create(final @NotNull Plugin plugin, final @NotNull Component debugFormat) {
        return new Debugger(plugin, debugFormat);
    }

    /**
     * Sends a debug message to all the registered players listening to the specified debugging channel.
     *
     * @param key     the identifier of the debug channel.
     * @param message the debug message.
     * @return {@code true} if the sending was successful, {@code false} if the channel is not registered.
     * @since 1.0.0
     */
    public boolean sendDebug(final @NotNull String key, final @NotNull String message) {
        final DebugChannel channel = getChannel(key);
        if (channel == null) return false;

        final Component debug = debugMessage.replaceText(
                TextReplacementConfig.builder()
                        .match("%channel%")
                        .replacement(channel.getFriendlyName())
                        .build()
        ).replaceText(
                TextReplacementConfig.builder()
                        .match("%message%")
                        .replacement(message)
                        .build()
        );

        for (final Player player : channel.getListeners()) {
            if (!player.isOnline()) unsubscribe(key, player);
            else player.sendMessage(debug);
        }

        if (!key.equals("*")) sendToAllDebug(message);
        return true;
    }

    /**
     * Returns the debug channel associated with the specified key.
     *
     * @param key the identifier of the debug channel.
     * @return the debug channel, or {@code null} if the channel is not registered.
     * @since 1.0.0
     */
    public @Nullable DebugChannel getChannel(final @NotNull String key) {
        return channels.get(key);
    }

    /**
     * Subscribes a player as a listener to the specified debug channel.
     *
     * @param key    the identifier of the debug channel.
     * @param player the player to subscribe.
     * @return {@code true} if the player was subscribed successfully, {@code false} if the channel is not registered.
     * @since 1.0.0
     */
    public boolean subscribe(final @NotNull String key, final @NotNull Player player) {
        final DebugChannel channel = Preconditions.checkNonNull(getChannel(key));
        return channel.addListener(player);
    }

    /**
     * Unsubscribes a player from the specified debug channel.
     *
     * @param key    the identifier of the debug channel.
     * @param player the player to unsubscribe.
     * @return {@code true} if the player was unsubscribed successfully, {@code false} if the channel is not registered.
     * @since 1.0.0
     */
    public boolean unsubscribe(final @NotNull String key, final @NotNull Player player) {
        final DebugChannel channel = Preconditions.checkNonNull(getChannel(key));
        return channel.removeListener(player);
    }

    /**
     * Registers a debug channel with the specified identifier and friendly name.
     *
     * @param key          the identifier of the debug channel.
     * @param friendlyName the friendly name of the debug channel.
     * @since 1.0.0
     */
    public void register(final @NotNull String key, final @NotNull String friendlyName) {
        channels.put(key, new DebugChannel(friendlyName));
    }

    /**
     * Sends a debug message to the debug channel that receives every debug message sent.
     *
     * @param message the debug message.
     * @since 1.0.0
     */
    private void sendToAllDebug(final @NotNull String message) {
        final DebugChannel channel = getChannel("*");
        assert channel != null; // Should never happen, but added for safety.

        final Component debug = debugMessage.replaceText(
                TextReplacementConfig.builder()
                        .match("%channel%")
                        .replacement(channel.getFriendlyName())
                        .build()
        ).replaceText(
                TextReplacementConfig.builder()
                        .match("%message%")
                        .replacement(message)
                        .build()
        );

        for (final Player player : channel.getListeners()) {
            if (!player.isOnline()) unsubscribe("*", player);
            else player.sendMessage(debug);
        }
    }

    /**
     * Initializes the debugger instance.
     *
     * @param plugin    the plugin using the debugger.
     * @param component the message format

    .
     * @since 1.0.0
     */
    private void init(final @NotNull Plugin plugin, final @NotNull Component component) {
        debugMessage = component.replaceText(
                TextReplacementConfig.builder()
                        .match("%plugin%")
                        .replacement(plugin.getName())
                        .build()
        );

        channels.put("*", new DebugChannel("*"));
        plugin.getLogger().info("Debugger has been initialized for the plugin '" + plugin.getName() + "'.");
    }

    /**
     * An object representing a debug channel.
     *
     * <p>
     * This class encapsulates a debug channel with a friendly name and a set of listeners.
     * The debug channel can be used to track and manage debugging information in a specific context.
     * </p>
     *
     * <p>
     * This class is designed to be used internally within the application and is not intended for direct public use.
     * </p>
     *
     * <p>
     * Thread Safety: This class is not thread-safe as the underlying set of listeners is not synchronized.
     * If multiple threads access the same DebugChannel instance concurrently and modify the listeners set,
     * external synchronization must be applied to ensure thread safety.
     * </p>
     *
     * <p>
     * Example usage:
     * <pre>{@code
     * DebugChannel channel = new DebugChannel("channel_name");
     * channel.addListener(player1);
     * channel.addListener(player2);
     * Set<Player> listeners = channel.getListeners();
     * }</pre>
     * </p>
     *
     * @author SadGhost
     * @since 1.0.0
     */
    private static final class DebugChannel {
        @NotNull private final String friendlyName;
        @NotNull private final Set<Player> listeners;

        /**
         * Constructs a DebugChannel object with the specified friendly name.
         *
         * @param friendlyName the friendly name of the debug channel.
         */
        @Contract(pure = true)
        private DebugChannel(final @NotNull String friendlyName) {
            this.friendlyName = friendlyName;
            this.listeners = new HashSet<>();
        }

        /**
         * Returns a set containing all the listeners of the debug channel.
         *
         * @return the set of listeners.
         * @since 1.0.0
         */
        @Contract(pure = true)
        public @NotNull Set<Player> getListeners() {
            return listeners;
        }

        /**
         * Adds a player as a listener to the debug channel.
         *
         * @param player the player to add as a listener.
         * @return {@code true} if the player was added as a listener, {@code false} if the player was already a listener.
         * @since 1.0.0
         */
        public boolean addListener(final @NotNull Player player) {
            return listeners.add(player);
        }

        /**
         * Removes a player from the listeners of the debug channel.
         *
         * @param player the player to remove from the listeners.
         * @return {@code true} if the player was removed from the listeners, {@code false} if the player was not a listener.
         * @since 1.0.0
         */
        public boolean removeListener(final @NotNull Player player) {
            return listeners.remove(player);
        }

        /**
         * Returns the friendly name of the debug channel.
         *
         * @return the friendly name of the channel.
         * @since 1.0.0
         */
        @Contract(pure = true)
        public @NotNull String getFriendlyName() {
            return friendlyName;
        }
    }
}
