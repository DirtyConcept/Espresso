package dev.sadghost.ghostingly.paper;

import dev.sadghost.ghostingly.base.Preconditions;
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
 * A debugger class which allows to create a consistent
 * plugin debugger instance with channels & everything.
 *
 * @author SadGhost
 * @since 1.0.0
 */
@ApiStatus.Experimental
public final class Debugger {
    @NotNull private final Map<String, DebugChannel> channels;
    /**
     * The default debug message format.
     * @since 1.0.0
     */
    @NotNull private static Component debugMessage = Component.text("[DEBUG:%plugin%] [%channel%] %message%");

    private Debugger(final @NotNull Plugin plugin) {
        this(plugin, debugMessage);
    }

    private Debugger(final @NotNull Plugin plugin,
                     final @NotNull Component debugFormat) {
        channels = new HashMap<>();
        init(plugin, debugFormat);
    }

    /**
     * Creates a debugger instance using the provided
     * plugin.
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
     * Creates a debugger instance using the provided
     * plugin and debug message format.
     *
     * @param plugin the plugin using the debugger.
     * @param debugFormat the debug message format.
     * @return the debugger instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> new")
    public static @NotNull Debugger create(final @NotNull Plugin plugin,
                                           final @NotNull Component debugFormat) {
        return new Debugger(plugin, debugFormat);
    }

    /**
     * Sends a debug message to all the registered players
     * to that debugging channel.
     *
     * @param key The debug channel identifier.
     * @param message The debug message.
     * @return If the sending was success.
     * @throws NullPointerException if the channel is not registered.
     * @since 1.0.0
     */
    public boolean sendDebug(final @NotNull String key,
                             final @NotNull String message) {
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
     * Returns the channel from the registry using the
     * identifier key provided in the parameters.
     *
     * @param key the identification key.
     * @return the debug channel.
     * @throws NullPointerException if the channel doesn't exist.
     * @since 1.0.0
     */
    public @Nullable DebugChannel getChannel(final @NotNull String key) {
        return channels.get(key);
    }

    /**
     * Subscribes a target as a listener to a channel.
     *
     * @param key The channel identifier.
     * @param player The target.
     * @return If the target was subscribed.
     * @since 1.0.0
     */
    public boolean subscribe(final @NotNull String key,
                             final @NotNull Player player) {
        final DebugChannel channel = Preconditions.checkNonNull(getChannel(key));
        return channel.addListener(player);
    }

    /**
     * Unsubscribes a listening target from the channel
     * key that is provided.
     *
     * @param key The channel identifier.
     * @param player The target.
     * @return if the target was unsubscribed.
     * @since 1.0.0
     */
    public boolean unsubscribe(final @NotNull String key,
                               final @NotNull Player player) {
        final DebugChannel channel = Preconditions.checkNonNull(getChannel(key));
        return channel.removeListener(player);
    }

    /**
     * Registers a debugger with an identification.
     *
     * @param key The channel identifier.
     * @since 1.0.0
     */
    public void register(final @NotNull String key,
                         final @NotNull String friendlyName) {
        channels.put(key, new DebugChannel(friendlyName));
    }

    /**
     * Sends a debug message to the debug channel which
     * receives every debug message sent.
     *
     * @param message the message.
     * @since 1.0.0
     */
    private void sendToAllDebug(final @NotNull String message) {
        final DebugChannel channel = getChannel("*");
        assert channel != null; // Never supposed to happen but still put just in case of a memory value deleting for no reason.

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
     * Initialization of the instance.
     *
     * @param plugin the plugin using the debugger.
     * @param component the message format.
     * @since 1.0.0
     */
    private void init(final @NotNull Plugin plugin,
                      final @NotNull Component component) {
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
     * An object referring to a debug channel.
     *
     * @since 1.0.0
     * @author SadGhost
     */
    private static final class DebugChannel {
        @NotNull private final String friendlyName;
        @NotNull private final Set<Player> listeners;

        @Contract(pure = true)
        private DebugChannel(final @NotNull String friendlyName) {
            this.friendlyName = friendlyName;
            this.listeners = new HashSet<>();
        }

        /**
         * Returns a set containing all the listening targets
         * to the channel.
         *
         * @return the listeners set.
         * @since 1.0.0
         */
        @Contract(pure = true)
        public @NotNull Set<Player> getListeners() {
            return listeners;
        }

        /**
         * Adds a target to the listeners of the channel.
         *
         * @param player the target.
         * @return if the target was added.
         * @since 1.0.0
         */
        public boolean addListener(final @NotNull Player player) {
            return listeners.add(player);
        }

        /**
         * Removes a target from the listeners of the channel.
         *
         * @param player the target.
         * @return if the target was removed.
         * @since 1.0.0
         */
        public boolean removeListener(final @NotNull Player player) {
            return listeners.remove(player);
        }

        /**
         * Returns the friendly name of the channel.
         *
         * @return channel friendly name.
         * @since 1.0.0
         */
        @Contract(pure = true)
        public @NotNull String getFriendlyName() {
            return friendlyName;
        }
    }
}
