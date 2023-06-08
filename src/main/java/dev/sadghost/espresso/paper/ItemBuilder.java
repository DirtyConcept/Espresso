package dev.sadghost.espresso.paper;

import com.destroystokyo.paper.Namespaced;
import com.google.common.collect.Multimap;
import dev.sadghost.espresso.base.Preconditions;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A builder class for creating ItemStack objects with custom properties.
 *
 * <p>
 * This class provides a convenient way to create ItemStack objects with various properties such as material, amount, and item meta.
 * It follows the builder pattern and allows chaining of method calls to configure the item.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>{@code
 * ItemStack item = ItemBuilder.of(Material.DIAMOND_SWORD)
 *                           .withAmount(1)
 *                           .withDisplayName("Sharp Sword")
 *                           .withLore("Powerful weapon")
 *                           .build();
 * }</pre>
 *
 * <p>
 * This class is immutable, meaning that once an ItemBuilder object is created, its properties cannot be changed.
 * To modify the properties, a new ItemBuilder object should be created.
 * </p>
 *
 * <p>
 * Thread Safety: This class is not thread-safe. It should not be accessed concurrently from multiple threads.
 * </p>
 *
 * @author SadGhost
 * @since 1.0.0
 */
public final class ItemBuilder {
    @NotNull private final ItemStack itemStack;
    @NotNull private final ItemMeta meta;

    /**
     * Creates an ItemBuilder object with a default amount of 1 from the provided material.
     *
     * @param material the material of the item.
     */
    public ItemBuilder(final @NotNull Material material) {
        this(material, 1);
    }

    /**
     * Creates an ItemBuilder object with the provided amount and material.
     *
     * @param material the material of the item.
     * @param amount   the amount of the item.
     */
    public ItemBuilder(final @NotNull Material material,
                       final int amount) {
        this(new ItemStack(material, amount));
    }

    /**
     * Creates an ItemBuilder object with the provided ItemStack.
     *
     * @param itemStack the ItemStack to use.
     */
    public ItemBuilder(final @NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = Preconditions.checkNonNull(itemStack.getItemMeta());
    }

    /**
     * Returns a new ItemBuilder instance with the provided material.
     *
     * @param material the material of the item.
     * @return a new ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> new")
    public static @NotNull ItemBuilder of(final @NotNull Material material) {
        return new ItemBuilder(material);
    }

    /**
     * Returns a new ItemBuilder instance with the provided material and amount.
     *
     * @param material the material of the item.
     * @param amount   the amount of the item.
     * @return a new ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> new")
    public static @NotNull ItemBuilder of(final @NotNull Material material, final int amount) {
        return new ItemBuilder(material, amount);
    }

    /**
     * Returns a new ItemBuilder instance with the provided ItemStack.
     *
     * @param itemStack the ItemStack to use.
     * @return a new ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> new")
    public static @NotNull ItemBuilder of(final @NotNull ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    /**
     * Sets the display name of the item.
     *
     * @param displayName the display name of the item.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withName(final @NotNull Component displayName) {
        meta.displayName(displayName);
        return this;
    }


    /**
     * Sets the damage on the item.
     *
     * @param damage the damage on the item.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withDamage(final int damage) {
        if (!(meta instanceof Damageable damageable))
            throw new IllegalStateException("Cannot set the damage of a non-damageable item.");

        damageable.setDamage(damage);
        return this;
    }


    /**
     * Sets the lore of the item.
     *
     * @param lore the lore of the item.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withLore(final @NotNull Component @NotNull... lore) {
        meta.lore(Arrays.asList(lore));
        return this;
    }

    /**
     * Sets the lore of the item.
     *
     * @param lore the lore of the item.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withLore(final @NotNull List<Component> lore) {
        meta.lore(lore);
        return this;
    }

    /**
     * Removed the lore line from the item.
     *
     * @param line the lore line on the item.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withoutLoreLine(final @NotNull Component line) {
        final List<Component> lore = meta.lore();
        if (lore == null) return this;
        if (!lore.contains(line)) return this;

        lore.remove(line);
        meta.lore(lore);
        return this;
    }

    /**
     * Removes the lore line from the item using an index.
     *
     * @param index the lore line's index on the item
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withoutLoreLine(final int index) {
        final List<Component> lore = meta.lore();
        if (lore == null) return this;
        if (index < 0 || index > lore.size()) return this;

        lore.remove(index);
        meta.lore(lore);
        return this;
    }

    /**
     * Adds the lore line to the item.
     *
     * @param line the new lore line.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder addLoreLine(final @NotNull Component line) {
        final List<Component> lore = meta.lore();
        if (lore != null) {
            lore.add(line);
            meta.lore(lore);
        }

        return this;
    }

    /**
     * Adds the lore line to a specific position on the lore of the item
     *
     * @param line the new lore line.
     * @param pos the position on the lore.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> this")
    public @NotNull ItemBuilder withLoreLine(final @NotNull Component line,
                                             final int pos) {
        final List<Component> lore = meta.lore();
        if (lore != null) {
            lore.set(pos, line);
            meta.lore(lore);
        }
        return this;
    }

    /**
     * Sets the custom model data of the item.
     *
     * @param i the custom model data of the item.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withCustomModelData(final int i) {
        meta.setCustomModelData(i);
        return this;
    }

    /**
     * Adds an enchantment to the item.
     *
     * @param enchant the new enchantment.
     * @param level the enchantment level.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> this")
    public @NotNull ItemBuilder addEnchant(final @NotNull Enchantment enchant,
                                           final int level) {
        meta.addEnchant(enchant, level, true);
        return this;
    }

    /**
     * Removes an enchantment from the item.
     *
     * @param enchant the enchantment on the item.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withoutEnchant(final @NotNull Enchantment enchant) {
        meta.removeEnchant(enchant);
        return this;
    }

    /**
     * Adds item flags to the item.
     *
     * @param itemFlags the new item flags.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder addItemFlags(final @NotNull ItemFlag @NotNull... itemFlags) {
        meta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Removes item flags from the item.
     *
     * @param itemFlags the item flags on the item.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withoutItemFlags(@NotNull ItemFlag @NotNull... itemFlags) {
        meta.removeItemFlags(itemFlags);
        return this;
    }

    /**
     * Sets the breaking state of the item.
     *
     * @param unbreakable the new breaking state.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder asUnbreakable(final boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Adds an attribute modifier to the item.
     *
     * @param attribute the attribute of the modifier.
     * @param attributeModifier the attribute modifier.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> this")
    public @NotNull ItemBuilder addAttributeModifier(final @NotNull Attribute attribute,
                                                     final @NotNull AttributeModifier attributeModifier) {
        meta.addAttributeModifier(attribute, attributeModifier);
        return this;
    }

    /**
     * Sets the attribute modifiers of the item.
     *
     * @param attributeModifiers the attribute modifiers map.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withAttributeModifiers(final @Nullable Multimap<Attribute, AttributeModifier> attributeModifiers) {
        meta.setAttributeModifiers(attributeModifiers);
        return this;
    }

    /**
     * Removes the attribute modifier from the item.
     *
     * @param attribute the attribute of the modifier.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withoutAttributeModifier(final @NotNull Attribute attribute) {
        meta.removeAttributeModifier(attribute);
        return this;
    }

    /**
     * The equipment slot to remove attribute modifiers from on the item.
     *
     * @param equipmentSlot the equipment slot to remove modifiers from.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withoutAttributeModifier(final @NotNull EquipmentSlot equipmentSlot) {
        meta.removeAttributeModifier(equipmentSlot);
        return this;
    }

    /**
     * Removes a specific attribute modifier from the item.
     *
     * @param attribute the attribute of the modifier.
     * @param attributeModifier the attribute modifier to remove.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_, _ -> this")
    public @NotNull ItemBuilder withoutAttributeModifier(final @NotNull Attribute attribute,
                                                         final @NotNull AttributeModifier attributeModifier) {
        meta.removeAttributeModifier(attribute, attributeModifier);
        return this;
    }

    /**
     * Sets the destroyable block keys on the item.
     *
     * @param keys the destroyable block keys.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withDestroyableKeys(final @NotNull Collection<Namespaced> keys) {
        meta.setDestroyableKeys(keys);
        return this;
    }

    /**
     * Sets the placeable block keys on the item.
     *
     * @param keys the placeable block keys.
     * @return the ItemBuilder instance.
     * @since 1.0.0
     */
    @Contract("_ -> this")
    public @NotNull ItemBuilder withPlaceableKeys(final @NotNull Collection<Namespaced> keys) {
        meta.setPlaceableKeys(keys);
        return this;
    }

    /**
     * Builds and returns the final ItemStack.
     *
     * @return the final ItemStack.
     */
    public @NotNull ItemStack build() {
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}