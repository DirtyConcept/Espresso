package dev.sadghost.ghostingly.paper.files;

import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * A File Manager that makes it easier to manage your configuration files
 * for the plugin.
 *
 * @author LielAmar, SadGhost
 * @since 1.0.0
 */
@Experimental
public final class FileManager {
    @NotNull private final JavaPlugin plugin;
    @NotNull private final Map<String, IConfig> configs;

    public FileManager(final @NotNull JavaPlugin plugin) {
        this.plugin = plugin;
        this.configs = new HashMap<>();
    }


    /**
     * Returns a config object representing a single configuration.
     *
     * @param path the path to the config file.
     * @param fileName the name of the config file.
     * @return config object representing the given config.
     * @since 1.0.0
     */
    public @Nullable IConfig getConfig(final @NotNull String path,
                                       @NotNull String fileName) {
        IConfig config = this.configs.get(fileName);
        if (config != null) {
            return config;
        }

        final File configFile = this.getConfigFile(path, fileName);
        if (configFile == null) {
            return null;
        }

        // If the config doesn't already exist, we want to create it, copy the resource and set its header
        if (!configFile.exists()) {
            this.createFile(configFile, this.plugin.getResource(fileName));
        }

        config = loadConfig(configFile);
        this.configs.put(fileName, config);
        return config;
    }

    /**
     * Returns a config object representing a single configuration.
     *
     * @param fileName the name of the config file.
     * @return the config object.
     * @since 1.0.0
     */
    public @Nullable IConfig getConfig(final @NotNull String fileName) {
        return this.getConfig(this.plugin.getDataFolder().getPath(), fileName);
    }


    /**
     * Returns the File object of a config file.
     *
     * @param path the path to the config file.
     * @param fileName the name of the config file.
     * @return the file object of the given config.
     * @since 1.0.0
     */
    private @Nullable File getConfigFile(@NotNull String path,
                                         final @NotNull String fileName) {
        if (fileName.isEmpty()) return null;
        if (path.isEmpty()) return new File(this.plugin.getDataFolder() + File.separator + fileName);
        if (!path.endsWith("/")) path += File.separator;

        return new File(path + fileName);
    }

    /**
     * Creates a File object for a config file.
     *
     * @param configFile the file to create.
     * @param source an optional source to apply to the config.
     * @since 1.0.0
     */
    private void createFile(final @NotNull File configFile,
                            final @Nullable InputStream source) {
        if (configFile.exists()) {
            return;
        }

        try {
            final boolean createdDir = configFile.getParentFile().mkdir();
            final boolean createdFile = configFile.createNewFile();

            if (source != null) {
                this.loadSource(configFile, source);
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads a source stream to a file.
     *
     * @param configFile the file to load the source to.
     * @param source the source to load.
     * @since 1.0.0
     */
    private void loadSource(final @NotNull File configFile,
                            final @NotNull InputStream source) {
        if (!configFile.exists()) {
            return;
        }

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(source, StandardCharsets.UTF_8));
            final OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8);

            String line;
            while ((line = reader.readLine()) != null) {
                out.write(line + "\n");
            }

            out.close();
            reader.close();
            source.close();
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads the config by its type, or throws an exception
     * if support is not implemented for that type of file.
     *
     * @param file the config file.
     * @return The loaded configuration file.
     * @throws NotImplementedException if support is not implemented for the provided file's type.
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
