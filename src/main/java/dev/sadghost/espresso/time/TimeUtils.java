package dev.sadghost.espresso.time;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
 * String formattedTime = TimeUtils.formatTime(timeDuration);
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
    private TimeUtils() {
    }

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
     * Formats the given time duration in milliseconds into a human-readable string representation.
     *
     * @param time the time duration in milliseconds
     * @return a human-readable string representation of the time duration
     * @since 1.0.0
     */
    public static @NotNull String formatTime(long time) {
        if (time < 0) return "Forever";

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

        final StringBuilder formattedTime = new StringBuilder();
        if (years > 0) formattedTime.append(years).append(" year").append(years > 1 ? "s" : "").append(", ");
        if (months > 0) formattedTime.append(months).append(" month").append(months > 1 ? "s" : "").append(", ");
        if (weeks > 0) formattedTime.append(weeks).append(" week").append(weeks > 1 ? "s" : "").append(", ");
        if (days > 0) formattedTime.append(days).append(" day").append(days > 1 ? "s" : "").append(", ");
        if (hours > 0) formattedTime.append(hours).append(" hour").append(hours > 1 ? "s" : "").append(", ");
        if (minutes > 0) formattedTime.append(minutes).append(" minute").append(minutes > 1 ? "s" : "").append(", ");
        if (seconds > 0) formattedTime.append(seconds).append(" second").append(seconds > 1 ? "s" : "");

        final int length = formattedTime.length();
        if (length >= 2 && formattedTime.charAt(length - 2) == ',') {
            formattedTime.replace(length - 2, length, "");
        }

        return formattedTime.toString();
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