package dev.sadghost.ghostingly.paper;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A directions calculation utility class.
 *
 * @author SadGhost
 * @since 1.0.0
 */
public final class DirectionUtils {

    /**
     * Seals the class.
     */
    @Contract(pure = true)
    private DirectionUtils() {}

    /**
     * Calculates a yaw from 2 locations
     *
     * @param point1 The first location
     * @param point2 The second location
     * @return Yaw angle
     * @since 1.0.0
     */
    public static float getAngle(final @NotNull Vector point1,
                                 final @NotNull Vector point2) {
        final double dx = point2.getX() - point1.getX();
        final double dz = point2.getZ() - point1.getZ();
        float angle = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90;

        if (angle < 0) angle += 360.0F;
        return angle;
    }

    /**
     * Returns the direction in which a player is relevant to a location
     *
     * @param player Player to check
     * @param to Destination
     * @return Direction
     * @since 1.0.0
     */
    public static @NotNull Direction getDirectionFromLocations(final @NotNull Player player,
                                                               final @NotNull Location to) {
        if (player.getWorld() != to.getWorld()) return Direction.X;
        final Vector vector = to.toVector().subtract(player.getLocation().toVector());
        final Vector direction = player.getEyeLocation().getDirection();
        final double angle = vector.angle(direction);
        final double deg = angle * 180 / Math.PI;

        if (deg <= 22) return Direction.N;
        else if (deg <= 67) return Direction.NE;
        else if (deg <= 112) return Direction.E;
        else if (deg <= 157) return Direction.SE;
        else if (deg <= 202) return Direction.S;
        else if (deg <= 247) return Direction.SW;
        else if (deg <= 292) return Direction.W;
        else if (deg <= 337) return Direction.NW;
        else if (deg <= 360) return Direction.N;

        return Direction.X;
    }
}
