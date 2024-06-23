package dev.sadghost.espresso.files;

import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.configuration.ConfigurationSection;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for managing configuration files of different types.
 * <p>
 * Implementations of this interface represent different file types received
 * from the file manager. They provide methods to retrieve and manipulate
 * configuration values based on their data types.
 *
 * @apiNote This interface is experimental and subject to change in future versions.
 * @since 1.0.0
 * @author SadGhost
 */
@Experimental
public sealed interface IConfig permits YAMLConfig {

    // Methods for retrieving configuration values

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

    @Nullable List<?> getList(@NotNull String path);
    @NotNull List<?> getList(@NotNull String path, @NotNull List<?> defaultValue);

    @NotNull Map<String, Object> getMapped(@NotNull String path);
    @NotNull Map<String, Object> getMapped(@NotNull String path, @NotNull Map<String, Object> defaultValue);


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

    @NotNull
    ConfigurationSection createSection(@NotNull String path);
    @Nullable
    ConfigurationSection getConfigurationSection(@NotNull String path);

    boolean contains(@NotNull String path);
    void removeKey(@NotNull String path);
    void set(@NotNull String path, @Nullable Object value);

    boolean isString(@NotNull String path);
    boolean isInt(@NotNull String path);
    boolean isDouble(@NotNull String path);
    boolean isLong(@NotNull String path);
    boolean isBoolean(@NotNull String path);
    boolean isList(@NotNull String path);
    boolean isConfigurationSection(@NotNull String path);
    boolean isSet(@NotNull String path);

    // Configuration file management methods

    /**
     * Reloads the configuration from the saved file on disk.
     * <p>
     * This method discards any changes made to the configuration and
     * reloads the values from the original file.
     */
    void reloadConfig();

    /**
     * Saves the configuration to the underlying file.
     * <p>
     * This method saves the current configuration values to the original file
     * on disk, overwriting any existing data in the file.
     */
    void saveConfig();
}
