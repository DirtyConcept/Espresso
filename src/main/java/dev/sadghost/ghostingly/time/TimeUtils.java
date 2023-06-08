package dev.sadghost.ghostingly.time;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class used for formatting time & much more.
 *
 * @author SadGhost
 * @since 1.0.0
 */
public class TimeUtils {
    private static final long MILLISECONDS_IN_SECOND = 1000;
    private static final long MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * 60;
    private static final long MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * 60;
    private static final long MILLISECONDS_IN_DAY = MILLISECONDS_IN_HOUR * 24;
    private static final long MILLISECONDS_IN_WEEK = MILLISECONDS_IN_DAY * 7;
    private static final long MILLISECONDS_IN_MONTH = MILLISECONDS_IN_DAY * 30;
    private static final long MILLISECONDS_IN_YEAR = MILLISECONDS_IN_DAY * 365;

    /**
     * Seals the class.
     */
    @Contract(pure = true)
    private TimeUtils() {}

    /**
     * Returns the current date
     *
     * @param pattern Pattern to use
     * @return Current date (String Format)
     * @since 1.0.0
     */
    public static @NotNull String getDate(final @NotNull String pattern) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(LocalDateTime.now());
    }

    /**
     * Parses time given in string into milliseconds as long
     * Example: 1y2m -> 100000
     *
     * @param time Time to parse
     * @return Parsed time as long
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
     * Util function to parse a specific time in string
     * Example: 1y -> 90000
     *
     * @param timeArray Array of characters to parse
     * @param index Current index in the array
     * @param millisecondsModifier Modifier to milliseconds - by how much does this function need to multiply the result to turn it to milliseconds
     * @return Parsed specific time as long
     * @since 1.0.0
     */
    private static long parseSpecificTime(final char @NotNull[] timeArray,
                                          final int index,
                                          final long millisecondsModifier) {
        long milliseconds = 0;
        int j = index - 1;
        int counter = 1;

        while (j >= 0 && Character.isDigit(timeArray[j])) {
            milliseconds += counter * Integer.parseInt(timeArray[j] + "") * millisecondsModifier;
            counter = counter * 10;
            j--;
        }

        return milliseconds;
    }

    /**
     * Parses time given in milliseconds into text as string
     * Example: 100000 -> 1 year, 2 months etc.
     *
     * @param time Time to parse
     * @return Parsed time as string
     * @since 1.0.0
     */
    public static @NotNull String parseTime(long time) {
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

        final StringBuilder finalTime = new StringBuilder();
        if (years > 0) finalTime.append(years).append(" years, ");
        if (months > 0) finalTime.append(months).append(" months, ");
        if (weeks > 0) finalTime.append(weeks).append(" weeks, ");
        if (days > 0) finalTime.append(days).append(" days, ");
        if (hours > 0) finalTime.append(hours).append(" hours, ");
        if (minutes > 0) finalTime.append(minutes).append(" minutes, ");
        if (seconds > 0) finalTime.append(seconds).append(" seconds");

        return finalTime.substring(0, finalTime.length() - 2);
    }

    /**
     * Formats X seconds to the following format: XX:XX
     *
     * @param seconds Seconds to format
     * @return Time left in the XX:XX format
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