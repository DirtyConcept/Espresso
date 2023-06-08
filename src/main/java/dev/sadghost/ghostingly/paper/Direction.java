package dev.sadghost.ghostingly.paper;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Directions with key names.
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

    @NotNull private final String rawName;

    @Contract(pure = true)
    Direction(final @NotNull String rawName) {
        this.rawName = rawName;
    }

    public @NotNull String getRawName() {
        return rawName;
    }
}
