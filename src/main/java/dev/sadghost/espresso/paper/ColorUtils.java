package dev.sadghost.espresso.paper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.DyeColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for handling color formatting and translation using the Kyori Adventure library.
 * Supports servers running Paper, Velocity, or any software that supports Kyori Adventure.
 *
 * @author SadGhost, LielAmar
 * @since 1.0.0
 */
public final class ColorUtils {

    /**
     * The most common color translation character.
     *
     * @since 1.0.0
     */
    public static final char COLOR_CHAR = '&';

    /**
     * Private constructor to prevent instantiation.
     */
    @Contract(pure = true)
    private ColorUtils() {}

    /**
     * Colors a message that uses Bungee's color format and returns the formatted message deserialized into a {@code Component}.
     *
     * @param text The Bungee format message
     * @return The formatted message as a {@code Component}
     * @since 1.0.0
     */
    public static @NotNull Component color(final @Nullable String text) {
        return color(COLOR_CHAR, text);
    }

    /**
     * Colors a message that uses Bukkit's color format and returns the formatted message deserialized into a {@code Component}.
     *
     * @param colorChar The color character to use in the deserialization
     * @param text      The Bukkit format message
     * @return The formatted message as a {@code Component}
     * @since 1.0.0
     */
    public static @NotNull Component color(final char colorChar, final @Nullable String text) {
        if (text == null) return Component.empty();
        return LegacyComponentSerializer.legacy(colorChar).deserialize(text);
    }

    /**
     * Colors a Bukkit message with support for hex color codes.
     *
     * @param text Message to color
     * @return Colored message
     * @deprecated in favor of {@link #color(String)}
     * @see #color(char, String)
     * @since 1.0.0
     */
    @Deprecated(since = "1.0.0")
    public static @NotNull String colorLegacy(final @Nullable String text) {
        return colorLegacy(COLOR_CHAR, text);
    }

    /**
     * Colors a Bukkit message with support for hex color codes.
     *
     * @param colorChar The color character
     * @param text      Message to color
     * @return Colored message
     * @deprecated in favor of {@link #color(char, String)}
     * @since 1.0.0
     */
    @Deprecated(since = "1.0.0")
    public static @NotNull String colorLegacy(final char colorChar, final @Nullable String text) {
        return text == null ? "" : ChatColor.translateAlternateColorCodes(colorChar, text);
    }

    /**
     * Removes all colors from the given string and replaces them with the color character.
     *
     * @param text Text to replace
     * @return Uncolored message
     * @deprecated in favor of {@link LegacyComponentSerializer#deserialize(String)}
     * @see #uncolorLegacy(char, String)
     * @since 1.0.0
     */
    @Deprecated(since = "1.0.0")
    public static @NotNull String uncolorLegacy(final @Nullable String text) {
        return uncolorLegacy(COLOR_CHAR, text);
    }

    /**
     * Removes all colors from the given string and replaces them with the color character.
     *
     * @param colorChar The color code character
     * @param text      Text to replace
     * @return Uncolored message
     * @deprecated in favor of {@link LegacyComponentSerializer#deserialize(String)}
     * @since 1.0.0
     */
    @Deprecated(since = "1.0.0")
    public static @NotNull String uncolorLegacy(final char colorChar, final @Nullable String text) {
        if (text == null) return "";

        final char[] array = text.toCharArray();
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == colorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(array[i + 1]) != -1) {
                array[i] = colorChar;
                array[i + 1] = Character.toLowerCase(array[i + 1]);
            }
        }
        return new String(array);
    }

    /**
     * Translates a DyeColor object to an integer.
     *
     * @param color The {@link DyeColor} object
     * @return Integer equivalent of the color
     * @throws IllegalArgumentException if {@code color} is null
     * @since 1.0.0
     */
    public static int translateColorToInt(final @NotNull DyeColor color) {
        return switch (color) {
            case WHITE -> 0;
            case ORANGE -> 1;
            case MAGENTA -> 2;
            case LIGHT_BLUE -> 3;
            case YELLOW -> 4;
            case LIME -> 5;
            case PINK -> 6;
            case GRAY -> 7;
            case LIGHT_GRAY -> 8;
            case CYAN -> 9;
            case PURPLE -> 10;
            case BLUE -> 11;
            case BROWN -> 12;
            case GREEN -> 13;
            case RED -> 14;
            case BLACK -> 15;
        };
    }

    /**
     * Translates an integer to a DyeColor object.
     *
     * @param color The color's id
     * @return {@link DyeColor} equivalent of the color
     * @since 1.0.0
     */
    public static @Nullable DyeColor translateIntToColor(final int color) {
        return switch (color) {
            case 0 -> DyeColor.WHITE;
            case 1 -> DyeColor.ORANGE;
            case 2 -> DyeColor.MAGENTA;
            case 3 -> DyeColor.LIGHT_BLUE;
            case 4 -> DyeColor.YELLOW;
            case 5 -> DyeColor.LIME;
            case 6 -> DyeColor.PINK;
            case 7 -> DyeColor.GRAY;
            case 8 -> DyeColor.LIGHT_GRAY;
            case 9 -> DyeColor.CYAN;
            case 10 -> DyeColor.PURPLE;
            case 11 -> DyeColor.BLUE;
            case 12 -> DyeColor.BROWN;
            case 13 -> DyeColor.GREEN;
            case 14 -> DyeColor.RED;
            case 15 -> DyeColor.BLACK;
            default -> null;
        };
    }
}
