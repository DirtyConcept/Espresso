package dev.sadghost.ghostingly.paper.files;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for all the file types received from the
 * file manager.
 *
 * @author SadGhost
 * @since 1.0.0
 */
@Experimental
public sealed interface IConfig permits YAMLConfig {
    @Nullable Object get(@NotNull String path);
    @NotNull Object get(@NotNull String path, @NotNull Object defaultValue);

    @Nullable String getString(@NotNull String path);
    @NotNull String getString(@NotNull String path, @NotNull String defaultValue);

    int getInt(@NotNull String path);
    int getInt(@NotNull String path, int defaultValue);

    double getDouble(@NotNull String path);
    double getDouble(@NotNull String path, double defaultValue);

    long getLong(@NotNull String path);
    long getLong(@NotNull String path, long defaultValue);

    boolean getBoolean(@NotNull String path);
    boolean getBoolean(@NotNull String path, boolean defaultValue);

    @Nullable Location getLocation(@NotNull String path);
    @NotNull Location getLocation(@NotNull String path, @NotNull Location defaultValue);

    @Nullable OfflinePlayer getOfflinePlayer(@NotNull String path);
    @NotNull OfflinePlayer getOfflinePlayer(@NotNull String path, @NotNull OfflinePlayer defaultValue);

    @Nullable Vector getVector(@NotNull String path);
    @NotNull Vector getVector(@NotNull String path, @NotNull Vector defaultValue);

    @Nullable ItemStack getItemStack(@NotNull String path);
    @NotNull ItemStack getItemStack(@NotNull String path, @NotNull ItemStack defaultValue);

    @Nullable List<?> getList(@NotNull String path);
    @NotNull List<?> getList(@NotNull String path, @NotNull List<?> defaultValue);

    @Nullable Color getColor(@NotNull String path);
    @NotNull Color getColor(@NotNull String path, @NotNull Color defaultValue);

    @Nullable <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz);
    @NotNull <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz, @NotNull T defaultValue);

    @NotNull List<String> getStringList(@NotNull String path);
    @NotNull List<Integer> getIntegerList(@NotNull String path);
    @NotNull List<Double> getDoubleList(@NotNull String path);
    @NotNull List<Float> getFloatList(@NotNull String path);
    @NotNull List<Boolean> getBooleanList(@NotNull String path);
    @NotNull List<Character> getCharacterList(@NotNull String path);
    @NotNull List<Long> getLongList(@NotNull String path);
    @NotNull List<Byte> getByteList(@NotNull String path);
    @NotNull List<Short> getShortList(@NotNull String path);
    @NotNull List<Map<?, ?>> getMapList(@NotNull String path);

    @NotNull Set<String> getKeys();
    @NotNull Set<String> getKeys(boolean deep);

    @NotNull ConfigurationSection createSection(@NotNull String path);
    @Nullable ConfigurationSection getConfigurationSection(@NotNull String path);

    boolean contains(@NotNull String path);
    void removeKey(@NotNull String path);
    void set(@NotNull String path, @Nullable Object value);

    boolean isString(@NotNull String path);
    boolean isInt(@NotNull String path);
    boolean isDouble(@NotNull String path);
    boolean isLong(@NotNull String path);
    boolean isBoolean(@NotNull String path);
    boolean isLocation(@NotNull String path);
    boolean isOfflinePlayer(@NotNull String path);
    boolean isVector(@NotNull String path);
    boolean isItemStack(@NotNull String path);
    boolean isColor(@NotNull String path);
    boolean isList(@NotNull String path);
    boolean isConfigurationSection(@NotNull String path);
    boolean isSet(@NotNull String path);

    /**
     * Reloads the config from the saved file on the disk
     */
    void reloadConfig();

    /**
     * Saves the config
     */
    void saveConfig();
}
