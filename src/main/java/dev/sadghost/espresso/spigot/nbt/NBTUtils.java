package dev.sadghost.espresso.spigot.nbt;

import dev.sadghost.espresso.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * An item NBT modification utility which uses the modern PDC methods to modify instead of NMS.
 * Provides methods to add and remove NBT tags from ItemStacks.
 * Note: Requires a Bukkit-based environment.
 *
 * @author SadGhost
 * @since 1.0.0
 */
public final class NBTUtils {

    /**
     * Seals the class to prevent instantiation.
     */
    @Contract(pure = true)
    private NBTUtils() {}

    /**
     * Adds an NBT tag to an ItemStack's metadata.
     *
     * @param itemStack the ItemStack to add the tag to
     * @param key the namespaced key of the tag.
     * @param type the type of tag being put into the item.
     * @param value the value being put into the tag.
     * @param <T> the value type in the NBT tag.
     * @param <U> the type it is represented by (example: boolean represented in byte).
     * @return the modified ItemStack with the added NBT tag
     * @since 1.0.0
     */
    @Contract("_, _, _, _ -> param1")
    public static <U, T> @NotNull ItemStack addTag(final @NotNull ItemStack itemStack,
                                                final @NotNull NamespacedKey key,
                                                final @NotNull PersistentDataType<U, T> type,
                                                final @NotNull T value) {
        Preconditions.checkNonNull(itemStack, "itemStack");
        Preconditions.checkNonNull(key, "key");
        Preconditions.checkNonNull(type, "type");
        Preconditions.checkNonNull(value, "value");

        final ItemMeta meta = Preconditions.checkNonNull(itemStack.getItemMeta());
        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(key, type, value);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     * Removes an NBT tag from an ItemStack's metadata.
     *
     * @param itemStack the ItemStack to remove the tag from
     * @param namespace the namespace of the NBT tag to remove
     * @return the modified ItemStack with the removed NBT tag
     * @since 1.0.0
     */
    @Contract("_, _ -> param1")
    public static @NotNull ItemStack removeTag(final @NotNull ItemStack itemStack,
                                               final @NotNull NamespacedKey namespace) {
        Preconditions.checkNonNull(itemStack, "itemStack");
        Preconditions.checkNonNull(namespace, "namespace");

        final ItemMeta meta = itemStack.getItemMeta();
        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(namespace);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     * Retrieves a tag of a specific type from an ItemStack's PersistentDataContainer.
     *
     * @param <T> the value type in the NBT tag.
     * @param <U> the type it is represented by.
     * @param itemStack The ItemStack to retrieve the tag from.
     * @param namespace The NamespacedKey representing the namespace of the tag.
     * @param type The PersistentDataType representing the type of the tag.
     * @return The NBTTag instance containing the retrieved tag, or null if the tag is not found or the value is null.
     * @since 1.0.1
     */
    public static <U, T> @Nullable NBTTag<U, T> getTag(final @NotNull ItemStack itemStack,
                                                       final @NotNull NamespacedKey namespace,
                                                       final @NotNull PersistentDataType<U, T> type) {
        Preconditions.checkNonNull(itemStack, "itemStack");
        Preconditions.checkNonNull(namespace, "namespace");
        Preconditions.checkNonNull(type, "type");

        final ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return null;

        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        if (!pdc.has(namespace, type)) return null;

        final T value = pdc.get(namespace, type);
        if (value == null) return null;

        final NBTTag.Builder<U, T> tagBuilder = NBTTag.builder();
        return tagBuilder.setKey(namespace)
                .setType(type)
                .setValue(value)
                .build();
    }

    /**
     * Clears all custom tags from an ItemStack's PersistentDataContainer.
     *
     * @param itemStack The ItemStack to clear the tags from.
     * @return A new ItemStack with all custom tags removed, or the original ItemStack if it has no ItemMeta.
     * @throws NullPointerException if the itemStack parameter is {@code null}.
     */
    public static @NotNull ItemStack clearTags(final @NotNull ItemStack itemStack) {
        Preconditions.checkNonNull(itemStack, "itemStack");

        final ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return itemStack;

        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        for (final NamespacedKey key : pdc.getKeys()) pdc.remove(key);

        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     * Checks whether the specified ItemStack contains a specific NBT tag.
     *
     * @param itemStack The ItemStack to check for the presence of the NBT tag.
     * @param tag The NBTTag representing the tag to search for.
     * @param <T> the value type in the NBT tag.
     * @param <U> the type it is represented by.
     * @return {@code true} if the ItemStack contains the specified NBT tag, {@code false} otherwise.
     * @throws NullPointerException if either the itemStack or tag is {@code null}.
     * @since 1.0.1
     */
    public static <U, T> boolean containsTag(final @NotNull ItemStack itemStack,
                                             final @NotNull NBTTag<U, T> tag) {
        Preconditions.checkNonNull(itemStack, "itemStack");
        Preconditions.checkNonNull(tag, "tag");

        return containsTag(itemStack, tag.getKey(), tag.getType());
    }

    /**
     * Checks if an ItemStack's PersistentDataContainer contains a tag with the specified namespace and type.
     *
     * @param itemStack The ItemStack to check for the presence of the tag.
     * @param namespace The NamespacedKey representing the namespace of the tag.
     * @param type The PersistentDataType representing the type of the tag.
     * @return {@code true} if a tag exists in the ItemStack's PersistentDataContainer with the specified namespace and type, {@code false} otherwise.
     * @throws NullPointerException if any of the parameters (itemStack, namespace, or type) is {@code null}.
     * @since 1.0.1
     */
    public static boolean containsTag(final @NotNull ItemStack itemStack,
                                      final @NotNull NamespacedKey namespace,
                                      final @NotNull PersistentDataType<?, ?> type) {
        Preconditions.checkNonNull(itemStack, "itemStack");
        Preconditions.checkNonNull(namespace, "namespace");
        Preconditions.checkNonNull(type, "type");

        final ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return false;

        return meta.getPersistentDataContainer().has(namespace, type);
    }

    /**
     * Retrieves all tags of a specific type from an ItemStack's PersistentDataContainer.
     *
     * @param <T> the value type in the NBT tag.
     * @param <U> the type it is represented by.
     * @param itemStack The ItemStack to retrieve tags from.
     * @param type The PersistentDataType representing the type of the tags to retrieve.
     * @return A list of NBTTag instances containing the retrieved tags.
     * @since 1.0.1
     */
    public static <U, T> @NotNull List<NBTTag<U, T>> getAllTags(final @NotNull ItemStack itemStack,
                                                                final @NotNull PersistentDataType<U, T> type) {
        final ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return new ArrayList<>();

        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        final List<NBTTag<U, T>> tags = new ArrayList<>();
        for (final NamespacedKey key : pdc.getKeys()) {
            if (pdc.has(key, type)) {
                final NBTTag.Builder<U, T> tagBuilder = NBTTag.builder();
                final T value = Preconditions.checkNonNull(pdc.get(key, type), "value");
                final NBTTag<U, T> tag = tagBuilder.setKey(key)
                        .setType(type)
                        .setValue(value)
                        .build();
                tags.add(tag);
            }
        }

        return tags;
    }
}
