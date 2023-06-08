package dev.sadghost.ghostingly.paper;

import dev.sadghost.ghostingly.paper.nbt.NBTTag;
import dev.sadghost.ghostingly.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * An item NBT modification utility which uses the modern
 * PDC methods to modify instead of NMS.
 *
 * @author SadGhost
 * @since 1.0.0
 */
public final class NBTUtils {

    /**
     * Seals the class.
     */
    @Contract(pure = true)
    private NBTUtils() {}

    /**
     * Gets an item and returns it with the NBT tag
     * added to its metadata.
     *
     * @param itemStack the item to add the tag to.
     * @param tag the NBT tag.
     * @param <T> the value type in the NBT tag.
     * @return the item with the modified nbt.
     * @since 1.0.0
     */
    @Contract("_, _ -> param1")
    public static <T> @NotNull ItemStack addTag(final @NotNull ItemStack itemStack,
                                                final @NotNull NBTTag<T> tag) {
        final ItemMeta meta = Preconditions.checkNonNull(itemStack.getItemMeta());
        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(tag.getKey(), tag.getType(), tag.getValue());
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     * Gets an item and returns it after removing the
     * NTB tag from the item's metadata.
     *
     * @param itemStack the item to remove the tag from.
     * @param namespace the NBT key to remove.
     * @return the item with the modified nbt.
     * @since 1.0.0
     */
    @Contract("_, _ -> param1")
    public static @NotNull ItemStack removeTag(final @NotNull ItemStack itemStack,
                                               final @NotNull NamespacedKey namespace) {
        final ItemMeta meta = itemStack.getItemMeta();
        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(namespace);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
