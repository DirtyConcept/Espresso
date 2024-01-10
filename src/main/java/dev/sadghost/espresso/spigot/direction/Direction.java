package dev.sadghost.espresso.spigot.direction;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Enum representing directions with key names.
 *
 * <p>
 * This enum provides a set of directions with associated key names.
 * Each direction is represented by an enum constant with its corresponding key name.
 * </p>
 *
 * <p>
 * This enum is used to represent directions in various contexts, such as player orientation or movement.
 * The enum constants also provide a method to retrieve the raw key name associated with each direction.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * Direction direction = Direction.N;
 * String keyName = direction.getRawName(); // "north"
 * }</pre>
 * </p>
 *
 * <p>
 * <b>Note:</b> The "X" direction represents an unknown or invalid direction.
 * </p>
 *
 * <p>
 * <b>Note:</b> The key names provided in this enum are arbitrary and may vary depending on the specific context or convention being used.
 * </p>
 *
 * <p>
 * <b>Note:</b> This enum can be extended to support additional directions if needed.
 * </p>
 *
 * @author SadGhost
 * @since 1.0.0
 */
public enum Direction {
    N("north"),
    NE("north_east"),
    E("east"),
    SE("south_east"),
    S("south"),
    SW("south_west"),
    W("west"),
    NW("north_west"),
    X("unknown_direction");

    @NotNull
    private final String rawName;

    /**
     * Constructs a Direction enum constant with the given raw key name.
     *
     * @param rawName the raw key name associated with the direction.
     */
    @Contract(pure = true)
    Direction(final @NotNull String rawName) {
        this.rawName = rawName;
    }

    /**
     * Retrieves the raw key name associated with the direction.
     *
     * @return the raw key name.
     */
    public @NotNull String getRawName() {
        return rawName;
    }
}

