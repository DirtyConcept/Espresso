package dev.sadghost.espresso.spigot.files;

import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The {@code FileManager} class is a file management utility that simplifies the management of
 * configuration files for the plugin. It provides methods for retrieving and creating configuration files,
 * loading configuration objects, and handling file operations.
 *
 * <p>This class is designed to be used within a plugin context and is specifically tailored for managing
 * configuration files in that context.
 *
 * <p>Usage Example:
 * <pre>{@code
 * FileManager fileManager = new FileManager(plugin);
 * IConfig config = fileManager.getConfig("config.yml");
 * }</pre>
 *
 * @author LielAmar, SadGhost
 * @since 1.0.0
 */
@Experimental
public final class FileManager {
    // Instance variables
    @NotNull private final JavaPlugin plugin;
    @NotNull private final Map<String, IConfig> configs;

    /**
     * Constructs a {@code FileManager} object with the specified {@code JavaPlugin} instance.
     *
     * @param plugin the JavaPlugin instance associated with this FileManager
     * @since 1.0.0
     */
    public FileManager(final @NotNull JavaPlugin plugin) {
        this.plugin = plugin;
        this.configs = new ConcurrentHashMap<>();
    }

    /**
     * Returns a configuration object representing a single configuration file.
     *
     * @param path the path to the configuration file
     * @param fileName the name of the configuration file
     * @return a configuration object representing the specified configuration file,
     *         or {@code null} if the file does not exist or an error occurred
     * @since 1.0.0
     */
    public @Nullable IConfig getConfig(final @NotNull String path,
                                       final @NotNull String fileName) {
        IConfig config = this.configs.get(fileName);
        if (config != null) return config;

        final File configFile = getConfigFile(path, fileName);
        if (configFile == null) return null;
        // If the config doesn't already exist, we want to create it, copy the resource and set its header
        if (!configFile.exists()) createFile(configFile, this.plugin.getResource(fileName));

        config = loadConfig(configFile);
        this.configs.put(fileName, config);
        return config;
    }

    /**
     * Returns a configuration object representing a single configuration file located in the plugin's data folder.
     *
     * @param fileName the name of the configuration file
     * @return a configuration object representing the specified configuration file,
     *         or {@code null} if the file does not exist or an error occurred
     * @since 1.0.0
     */
    public @Nullable IConfig getConfig(final @NotNull String fileName) {
        return getConfig(this.plugin.getDataFolder().getPath(), fileName);
    }

    /**
     * Returns the File object of a configuration file.
     *
     * @param path the path to the configuration file
     * @param fileName the name of the configuration file
     * @return the File object representing the specified configuration file,
     *         or {@code null} if the file name is empty
     * @since 1.0.0
     */
    private @Nullable File getConfigFile(final @NotNull String path,
                                         final @NotNull String fileName) {
        if (fileName.isEmpty()) return null;
        return new File(path, fileName);
    }

    /**
     * Creates a File object for a configuration file and optionally copies the source stream to the file.
     *
     * @param configFile the File object to create
     * @param source the optional source stream to apply to the configuration file
     * @since 1.0.0
     */
    private void createFile(final @NotNull File configFile,
                            final @Nullable InputStream source) {
        if (configFile.exists()) return;
        try {
            configFile.getParentFile().mkdir();
            configFile.createNewFile();

            if (source != null) {
                loadSource(configFile, source);
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads a source stream to a file.
     *
     * @param configFile the file to load the source stream to
     * @param source the source stream to load
     * @since 1.0.0
     */
    private void loadSource(final @NotNull File configFile,
                            final @NotNull InputStream source) {
        if (!configFile.exists()) return;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(source, StandardCharsets.UTF_8));
             final BufferedWriter out = new BufferedWriter(new FileWriter(configFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.write(line + "\n");
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads the configuration file based on its type, or throws a {@code NotImplementedException}
     * if support is not implemented for the provided file type.
     *
     * @param file the configuration file
     * @return the loaded configuration object
     * @throws NotImplementedException if support is not implemented for the provided file type
     * @since 1.0.0
     */
    @Contract("_ -> new")
    private @NotNull IConfig loadConfig(final @NotNull File file) {
        if (file.getName().toLowerCase().endsWith(".yml")) {
            return new YAMLConfig(file);
        }

        throw new NotImplementedException("This file type is not supported yet.");
    }
}
