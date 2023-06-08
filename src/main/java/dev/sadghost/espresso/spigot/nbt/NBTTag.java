package dev.sadghost.espresso.spigot.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * An object representing an NBT tag in the new NBT API known as PersistentDataContainer.
 *
 * @param <T> the value type in the NBT.
 * @author SadGhost
 * @since 1.0.0
 */
public final class NBTTag<T> {
    private final @NotNull NamespacedKey key;
    private final @NotNull PersistentDataType<T, T> type;
    private final @NotNull T value;

    @Contract(pure = true)
    NBTTag(final @NotNull NamespacedKey key,
           final @NotNull PersistentDataType<T, T> type,
           final @NotNull T value) {
        this.key = key;
        this.type = type;
        this.value = value;
    }

    /**
     * Returns a builder instance to construct an NBTTag.
     *
     * @param <T> the value type used for the NBT tag
     * @return a new instance of the Builder
     * @since 1.0.0
     */
    @Contract(" -> new")
    public static <T> @NotNull Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * Returns the namespace key of the NBT tag.
     *
     * @return the namespace key of the tag
     * @since 1.0.0
     */
    @Contract(pure = true)
    public @NotNull NamespacedKey getKey() {
        return key;
    }

    /**
     * Returns the data type used by the NBT tag.
     *
     * @return the data type used by the tag
     * @since 1.0.0
     */
    @Contract(pure = true)
    public @NotNull PersistentDataType<T, T> getType() {
        return type;
    }

    /**
     * Returns the value stored in the NBT tag.
     *
     * @return the value of the tag
     * @since 1.0.0
     */
    @Contract(pure = true)
    public @NotNull T getValue() {
        return value;
    }

    /**
     * A builder class for constructing NBTTag instances.
     *
     * @param <T> the value type used for the NBT tag
     * @since 1.0.0
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static final class Builder<T> {
        private Optional<NamespacedKey> key = Optional.empty();
        private Optional<PersistentDataType<T, T>> type = Optional.empty();
        private Optional<T> value = Optional.empty();

        /**
         * Sets the namespace key for the NBT tag.
         *
         * @param key the namespace key
         * @return the builder instance
         * @since 1.0.0
         */
        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder<T> setKey(final @NotNull NamespacedKey key) {
            this.key = Optional.of(key);
            return this;
        }

        /**
         * Sets the data type for the NBT tag.
         *
         * @param type the data type
         * @return the builder instance
         * @since 1.0.0
         */
        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder<T> setType(final @NotNull PersistentDataType<T, T> type) {
            this.type = Optional.of(type);
            return this;
        }

        /**
         * Sets the value for the NBT tag.
         *
         * @param value the value
         * @return the builder instance
         * @since 1.0.0
         */
        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder<T> setValue(final @NotNull T value) {
            this.value = Optional.of(value);
            return this;
        }

        /**
         * Constructs an NBTTag instance using the provided values.
         *
         * @return the constructed NBTTag instance
         * @throws IllegalStateException if any of the required values are missing
         * @since 1.0.0
         */
        @Contract(" -> new")
        public @NotNull NBTTag<T> build() {
            return new NBTTag<>(
                    key.orElseThrow(() -> new IllegalStateException("Missing 'key' for the tag.")),
                    type.orElseThrow(() -> new IllegalStateException("Missing 'type' for the tag.")),
                    value.orElseThrow(() -> new IllegalStateException("Missing 'value' for the tag."))
            );
        }
    }
}
