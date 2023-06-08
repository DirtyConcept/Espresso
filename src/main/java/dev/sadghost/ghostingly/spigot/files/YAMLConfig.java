package dev.sadghost.ghostingly.spigot.files;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A YAMLConfig object representing a .yml configuration file.
 * Used to access .yml files.
 *
 * @author LielAmar, SadGhost
 * @since 1.0.0
 */
@Experimental
public final class YAMLConfig implements IConfig {
    @NotNull private final File configFile;
    private YamlConfiguration configuration;
    
    YAMLConfig(final @NotNull File configFile) {
        this.configFile = configFile;

        this.loadConfig();
    }

    @Override
    public @Nullable Object get(final @NotNull String path) {
        return this.configuration.get(path);
    }
    @Override
    public @NotNull Object get(final @NotNull String path, final @NotNull Object defaultValue) {
        return this.configuration.get(path, defaultValue);
    }

    @Override
    public @Nullable String getString(final @NotNull String path) {
        return this.configuration.getString(path);
    }
    @Override
    public @NotNull String getString(final @NotNull String path, final @NotNull String defaultValue) {
        return this.configuration.getString(path, defaultValue);
    }

    @Override
    public int getInt(final @NotNull String path) {
        return this.configuration.getInt(path);
    }
    @Override
    public int getInt(final @NotNull String path, final int defaultValue) {
        return this.configuration.getInt(path, defaultValue);
    }

    @Override
    public double getDouble(final @NotNull String path) {
        return this.configuration.getDouble(path);
    }
    @Override
    public double getDouble(final @NotNull String path, final double defaultValue) {
        return this.configuration.getDouble(path, defaultValue);
    }

    @Override
    public long getLong(final @NotNull String path) {
        return this.configuration.getLong(path);
    }
    @Override
    public long getLong(final @NotNull String path, final long defaultValue) {
        return this.configuration.getLong(path, defaultValue);
    }

    @Override
    public boolean getBoolean(final @NotNull String path) {
        return this.configuration.getBoolean(path);
    }
    @Override
    public boolean getBoolean(final @NotNull String path, final boolean defaultValue) {
        return this.configuration.getBoolean(path, defaultValue);
    }

    @Override
    public @Nullable Location getLocation(final @NotNull String path) {
        return this.configuration.getLocation(path);
    }
    @Override
    public @NotNull Location getLocation(final @NotNull String path, final @NotNull Location defaultValue) {
        return this.configuration.getLocation(path, defaultValue);
    }

    @Override
    public @Nullable OfflinePlayer getOfflinePlayer(final @NotNull String path) {
        return this.configuration.getOfflinePlayer(path);
    }
    @Override
    public @NotNull OfflinePlayer getOfflinePlayer(final @NotNull String path, final @NotNull OfflinePlayer defaultValue) {
        return this.configuration.getOfflinePlayer(path, defaultValue);
    }

    @Override
    public @Nullable Vector getVector(final @NotNull String path) {
        return this.configuration.getVector(path);
    }
    @Override
    public @NotNull Vector getVector(final @NotNull String path, final @NotNull Vector defaultValue) {
        return this.configuration.getVector(path, defaultValue);
    }

    @Override
    public @Nullable ItemStack getItemStack(final @NotNull String path) {
        return this.configuration.getItemStack(path);
    }
    @Override
    public @NotNull ItemStack getItemStack(final @NotNull String path, final @NotNull ItemStack defaultValue) {
        return this.configuration.getItemStack(path, defaultValue);
    }

    @Override
    public @Nullable List<?> getList(final @NotNull String path) {
        return this.configuration.getList(path);
    }
    @Override
    public @NotNull List<?> getList(final @NotNull String path, final @NotNull List<?> defaultValue) {
        return this.configuration.getList(path, defaultValue);
    }

    @Override
    public @Nullable Color getColor(final @NotNull String path) {
        return this.configuration.getColor(path);
    }
    @Override
    public @NotNull Color getColor(final @NotNull String path, final @NotNull Color defaultValue) {
        return this.configuration.getColor(path, defaultValue);
    }

    @Override
    public @Nullable <T extends ConfigurationSerializable> T getSerializable(final @NotNull String path, final @NotNull Class<T> clazz) {
        return this.configuration.getSerializable(path, clazz);
    }
    @Override
    public @NotNull <T extends ConfigurationSerializable> T getSerializable(final @NotNull String path, final @NotNull Class<T> clazz, final @NotNull T defaultValue) {
        return this.configuration.getSerializable(path, clazz, defaultValue);
    }

    @Override
    public @NotNull List<String> getStringList(final @NotNull String path) {
        return this.configuration.getStringList(path);
    }

    @Override
    public @NotNull List<Integer> getIntegerList(final @NotNull String path) {
        return this.configuration.getIntegerList(path);
    }

    @Override
    public @NotNull List<Double> getDoubleList(final @NotNull String path) {
        return this.configuration.getDoubleList(path);
    }

    @Override
    public @NotNull List<Float> getFloatList(final @NotNull String path) {
        return this.configuration.getFloatList(path);
    }

    @Override
    public @NotNull List<Boolean> getBooleanList(final @NotNull String path) {
        return this.configuration.getBooleanList(path);
    }

    @Override
    public @NotNull List<Character> getCharacterList(final @NotNull String path) {
        return this.configuration.getCharacterList(path);
    }

    @Override
    public @NotNull List<Long> getLongList(final @NotNull String path) {
        return this.configuration.getLongList(path);
    }

    @Override
    public @NotNull List<Byte> getByteList(final @NotNull String path) {
        return this.configuration.getByteList(path);
    }

    @Override
    public @NotNull List<Short> getShortList(final @NotNull String path) {
        return this.configuration.getShortList(path);
    }

    @Override
    public @NotNull List<Map<?, ?>> getMapList(final @NotNull String path) {
        return this.configuration.getMapList(path);
    }

    @Override
    public @NotNull Set<String> getKeys() {
        return this.configuration.getKeys(false);
    }

    @Override
    public @NotNull Set<String> getKeys(final boolean deep) {
        return this.configuration.getKeys(deep);
    }

    @Override
    public @NotNull ConfigurationSection createSection(final @NotNull String path) {
        return this.configuration.createSection(path);
    }

    @Override
    public @Nullable ConfigurationSection getConfigurationSection(final @NotNull String path) {
        return this.configuration.getConfigurationSection(path);
    }

    @Override
    public boolean contains(final @NotNull String path) {
        return this.configuration.contains(path);
    }

    @Override
    public void removeKey(final @NotNull String path) {
        this.configuration.set(path, null);
    }

    @Override
    public void set(final @NotNull String path, final @Nullable Object value) {
        this.configuration.set(path, value);
    }

    @Override
    public boolean isString(final @NotNull String path) {
        return this.configuration.isString(path);
    }

    @Override
    public boolean isInt(final @NotNull String path) {
        return this.configuration.isInt(path);
    }

    @Override
    public boolean isDouble(final @NotNull String path) {
        return this.configuration.isDouble(path);
    }

    @Override
    public boolean isLong(final @NotNull String path) {
        return this.configuration.isLong(path);
    }

    @Override
    public boolean isBoolean(final @NotNull String path) {
        return this.configuration.isBoolean(path);
    }

    @Override
    public boolean isLocation(final @NotNull String path) {
        return this.configuration.isLocation(path);
    }

    @Override
    public boolean isOfflinePlayer(final @NotNull String path) {
        return this.configuration.isOfflinePlayer(path);
    }

    @Override
    public boolean isVector(final @NotNull String path) {
        return this.configuration.isVector(path);
    }

    @Override
    public boolean isItemStack(final @NotNull String path) {
        return this.configuration.isItemStack(path);
    }

    @Override
    public boolean isColor(final @NotNull String path) {
        return this.configuration.isColor(path);
    }

    @Override
    public boolean isList(final @NotNull String path) {
        return this.configuration.isList(path);
    }

    @Override
    public boolean isConfigurationSection(final @NotNull String path) {
        return this.configuration.isConfigurationSection(path);
    }

    @Override
    public boolean isSet(final @NotNull String path) {
        return this.configuration.isSet(path);
    }

    // Configuration file management methods

    /**
     * Loads the configuration file into the plugin's configuration object.
     * <p>
     * This method reads the config file and sets the value of the configuration object.
     * If the config file does not exist, this method does nothing.
     * <p>
     * Note: Comments in the config file will be ignored during the loading process.
     *
     * @since 1.0.0
     */
    private void loadConfig() {
        if (!this.configFile.exists()) return;

        try {
            String currentLine;

            final List<String> lines = Files.readAllLines(this.configFile.toPath(), StandardCharsets.UTF_8);
            final StringBuilder configData = new StringBuilder();

            for (final String line : lines) {
                currentLine = line.trim();

                if (!currentLine.startsWith("#")) {
                    // Append the line to the config data
                    configData.append(line).append("\n");
                }

            }

            // Load the configData into this.configuration through a reader
            final Reader inputString = new StringReader(configData.toString());
            final BufferedReader reader = new BufferedReader(inputString);

            this.configuration = YamlConfiguration.loadConfiguration(reader);

            // Close the reader
            inputString.close();
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns a key from a path.
     * <p>
     * If the path has any parents, we want to only get the children.
     * We also want to add 2 spaces before the key, for every parent it has.
     * This way, the key matches with the one from the initial load of the config (through {@link #loadConfig()}).
     * <p>
     * Example:
     *   hello.i.am.liel
     *   - 3 parents (hello, i, am)
     *   - key is "liel"
     *   - final key would have 6 spaces (3 parents * 2 spaces for each parent) -> "      liel"
     *
     * @param path the path to get the key of
     * @return the key of the path
     * @since 1.0.0
     */
    private @NotNull String getKeyFromPath(final @NotNull String path) {
        StringBuilder key;

        if (!path.contains(".")) {
            key = new StringBuilder(path);
        } else {
            final String[] pathParts = path.split("\\.");
            key = new StringBuilder(pathParts[pathParts.length - 1]);

            // Adding the spaces before the key
            for (int i = 0; i < pathParts.length - 1; i++) {
                key.insert(0, "  ");
            }
        }

        return key.toString();
    }

    /**
     * Reloads the configuration from the file.
     * <p>
     * This method reloads the configuration by calling {@link #loadConfig()}.
     *
     * @since 1.0.0
     */
    @Override
    public void reloadConfig() {
        this.loadConfig();
    }

    /**
     * Saves the configuration to the file.
     * <p>
     * This method saves the configuration by converting it to a string, applying comments,
     * and then writing it to the config file.
     *
     * @since 1.0.0
     */
    @Override
    public void saveConfig() {
        this.saveConfig(this.getConfigAsString());
    }

    /**
     * Saves the provided configuration string to the config file.
     *
     * @param configString the string to save to the config
     * @since 1.0.0
     */
    private void saveConfig(final @NotNull String configString) {
        try (final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.configFile), StandardCharsets.UTF_8))) {
            writer.write(configString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the configuration as a string.
     *
     * @return the string value of the config, including comments
     * @since 1.0.0
     */
    private @NotNull String getConfigAsString() {
        return this.configuration.saveToString();
    }
}