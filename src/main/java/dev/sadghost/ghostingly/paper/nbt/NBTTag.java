package dev.sadghost.ghostingly.paper.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * An object representing an NBT tag in the new NBT API known
 * as PersistentDataContainer.
 *
 * @param <T> the value type in the nbt.
 * @author SadGhost
 * @since 1.0.0
 */
public final class NBTTag<T> {
    @NotNull private final NamespacedKey key;
    @NotNull private final PersistentDataType<T, T> type;
    @NotNull private final T value;

    @Contract(pure = true)
    NBTTag(final @NotNull NamespacedKey key,
                  final @NotNull PersistentDataType<T, T> type,
                  final @NotNull T value) {
        this.key = key;
        this.type = type;
        this.value = value;
    }

    /**
     * Returns a builder instance.
     *
     * @param <T> the type used for the nbt tag.
     * @return the tag
     * @since 1.0.0
     */
    @Contract(" -> new")
    public static <T> @NotNull Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * Returns the NBT tag namespace key.
     *
     * @return the namespace key.
     * @since 1.0.0
     */
    @Contract(pure = true)
    public @NotNull NamespacedKey getKey() {
        return key;
    }

    /**
     * Returns the data type used for the nbt tag.
     *
     * @return the data type.
     * @since 1.0.0
     */
    @Contract(pure = true)
    public @NotNull PersistentDataType<T, T> getType() {
        return type;
    }

    /**
     * Returns the value that the tag holds.
     *
     * @return the tag's value.
     * @since 1.0.0
     */
    @Contract(pure = true)
    public @NotNull T getValue() {
        return value;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static final class Builder<T> {
        private Optional<NamespacedKey> key;
        private Optional<PersistentDataType<T, T>> type;
        private Optional<T> value;

        public Builder() {
            key = Optional.empty();
            type = Optional.empty();
            value = Optional.empty();
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder<T> setKey(final @NotNull NamespacedKey key) {
            this.key = Optional.of(key);
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder<T> setType(final @NotNull PersistentDataType<T, T> type) {
            this.type = Optional.of(type);
            return this;
        }

        @Contract(value = "_ -> this", mutates = "this")
        public @NotNull Builder<T> setValue(final @NotNull T value) {
            this.value = Optional.of(value);
            return this;
        }

        public NBTTag<T> build() {
            return new NBTTag<>(
                    key.orElseThrow(() -> new IllegalStateException("Missing 'key' for the tag.")),
                    type.orElseThrow(() -> new IllegalStateException("Missing 'type' for the tag.")),
                    value.orElseThrow(() -> new IllegalStateException("Missing 'value' for the tag."))
            );
        }
    }
}
