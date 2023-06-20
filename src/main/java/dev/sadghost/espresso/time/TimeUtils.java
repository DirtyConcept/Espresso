package dev.sadghost.espresso.time;

import dev.sadghost.espresso.base.Preconditions;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * The {@code TimeUtils} class is a utility class for formatting and manipulating time.
 * It provides methods for retrieving the current date, parsing time strings, formatting time durations,
 * and formatting seconds into a specific time format.
 * <p>
 * The class uses milliseconds as the standard unit of time for calculations and conversions.
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * String currentDate = TimeUtils.getCurrentDate("yyyy-MM-dd");
 * long timeDuration = TimeUtils.parseTime("1y2m");
 * String formattedTime = TimeUtils.formatTime(timeDuration, TimeUnit.MILLISECONDS, "%years%-%months%", null);
 * String formattedSeconds = TimeUtils.formatSeconds(300);
 * }</pre>
 *
 * @author SadGhost
 * @since 1.0.0
 */
public class TimeUtils {
    // Constants for milliseconds conversion
    private static final long MILLISECONDS_IN_SECOND = 1000;
    private static final long MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * 60;
    private static final long MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * 60;
    private static final long MILLISECONDS_IN_DAY = MILLISECONDS_IN_HOUR * 24;
    private static final long MILLISECONDS_IN_WEEK = MILLISECONDS_IN_DAY * 7;
    private static final long MILLISECONDS_IN_MONTH = MILLISECONDS_IN_DAY * 30;
    private static final long MILLISECONDS_IN_YEAR = MILLISECONDS_IN_DAY * 365;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    @Contract(pure = true)
    private TimeUtils() {}

    /**
     * Returns the current date formatted according to the given pattern.
     *
     * @param pattern the pattern to use for formatting the date
     * @return the current date as a string formatted according to the pattern
     * @since 1.0.0
     */
    public static @NotNull String getCurrentDate(final @NotNull String pattern) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(LocalDateTime.now());
    }

    /**
     * Parses the given time string and returns the corresponding duration in milliseconds.
     *
     * @param time the time string to parse
     * @return the parsed time duration in milliseconds
     * @since 1.0.0
     */
    public static long parseTime(final @NotNull String time) {
        long milliseconds = 0;
        final char[] timeArray = time.toCharArray();

        for (int i = 0; i < timeArray.length; i++) {
            switch (timeArray[i]) {
                case 's' -> milliseconds += parseSpecificTime(timeArray, i, MILLISECONDS_IN_SECOND);
                case 'm' -> milliseconds += parseSpecificTime(timeArray, i, MILLISECONDS_IN_MINUTE);
                case 'h' -> milliseconds += parseSpecificTime(timeArray, i, MILLISECONDS_IN_HOUR);
                case 'd' -> milliseconds += parseSpecificTime(timeArray, i, MILLISECONDS_IN_DAY);
                case 'w' -> milliseconds += parseSpecificTime(timeArray, i, MILLISECONDS_IN_WEEK);
                case 'M' -> milliseconds += parseSpecificTime(timeArray, i, MILLISECONDS_IN_MONTH);
                case 'y' -> milliseconds += parseSpecificTime(timeArray, i, MILLISECONDS_IN_YEAR);
            }
        }

        return milliseconds;
    }

    /**
     * Utility function to parse a specific time unit from the time string.
     *
     * @param timeArray the array of characters representing the time string
     * @param index the current index in the array
     * @param millisecondsModifier the multiplier to convert the result to milliseconds
     * @return the parsed specific time unit in milliseconds
     * @since 1.0.0
     */
    private static long parseSpecificTime(final char @NotNull[] timeArray,
                                          final int index,
                                          final long millisecondsModifier) {
        long milliseconds = 0;
        int j = index - 1;
        int counter = 1;

        while (j >= 0 && Character.isDigit(timeArray[j])) {
            milliseconds += counter * Integer.parseInt(String.valueOf(timeArray[j])) * millisecondsModifier;
            counter = counter * 10;
            j--;
        }

        return milliseconds;
    }

    /**
     * Formats the given time duration into a human-readable string representation.
     *
     * @param duration the time duration
     * @param timeUnit the time unit to use for formatting (e.g., seconds, minutes, hours)
     * @param formatPattern the format pattern for the time representation
     * @param foreverPlaceholder the placeholder for the representation of a negative duration
     * @throws NullPointerException if either of the provided {@code timeUnit} or {@code formatParam} are null
     * @return a human-readable string representation of the time duration
     * @since 1.0.0
     */
    public static @NotNull String formatTime(final long duration,
                                             final @NotNull TimeUnit timeUnit,
                                             final @NotNull String formatPattern,
                                             final @Nullable String foreverPlaceholder) {
        Preconditions.checkNonNull(timeUnit, "timeUnit cannot be null");
        Preconditions.checkNonNull(formatPattern, "formatPattern cannot be null");

        if (duration < 0) return foreverPlaceholder == null ? "Forever" : foreverPlaceholder;
        long time = timeUnit.convert(duration, TimeUnit.MILLISECONDS);

        final long years = time / MILLISECONDS_IN_YEAR;
        time -= MILLISECONDS_IN_YEAR * years;
        final long months = time / MILLISECONDS_IN_MONTH;
        time -= MILLISECONDS_IN_MONTH * months;
        final long weeks = time / MILLISECONDS_IN_WEEK;
        time -= MILLISECONDS_IN_WEEK * weeks;
        final long days = time / MILLISECONDS_IN_DAY;
        time -= MILLISECONDS_IN_DAY * days;
        final long hours = time / MILLISECONDS_IN_HOUR;
        time -= MILLISECONDS_IN_HOUR * hours;
        final long minutes = time / MILLISECONDS_IN_MINUTE;
        time -= MILLISECONDS_IN_MINUTE * minutes;
        final long seconds = time / MILLISECONDS_IN_SECOND;

        return formatPattern
                .replace("%years%", String.valueOf(years))
                .replace("%months%", String.valueOf(months))
                .replace("%weeks%", String.valueOf(weeks))
                .replace("%days%", String.valueOf(days))
                .replace("%hours%", String.valueOf(hours))
                .replace("%minutes%", String.valueOf(minutes))
                .replace("%seconds%", String.valueOf(seconds));
    }

    /**
     * Formats the given number of seconds into the "XX:XX" format.
     *
     * @param seconds the number of seconds to format
     * @return the formatted time in the "XX:XX" format
     * @since 1.0.0
     */
    @Contract(pure = true)
    public static @NotNull String formatSeconds(int seconds) {
        final int minutes = seconds / 60;
        seconds = seconds % 60;

        String sMinutes = String.valueOf(minutes);
        String sSeconds = String.valueOf(seconds);

        if (minutes < 10) sMinutes = "0" + minutes;
        if (seconds < 10) sSeconds = "0" + seconds;

        return sMinutes + ":" + sSeconds;
    }
}