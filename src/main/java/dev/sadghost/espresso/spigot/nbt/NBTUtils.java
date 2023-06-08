package dev.sadghost.espresso.spigot.nbt;

import dev.sadghost.espresso.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
     * @param tag       the NBT tag to add
     * @param <T>       the value type in the NBT tag
     * @return the modified ItemStack with the added NBT tag
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
        final ItemMeta meta = itemStack.getItemMeta();
        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(namespace);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
