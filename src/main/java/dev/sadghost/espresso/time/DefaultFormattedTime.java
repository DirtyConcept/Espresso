package dev.sadghost.espresso.time;

import org.jetbrains.annotations.NotNull;

public class DefaultFormattedTime implements TimeFormatter {

    DefaultFormattedTime() {}

    @Override
    public @NotNull String getFormattedTime(long years, long months, long weeks, long days,
                                            long hours, long minutes, long seconds) {
        StringBuilder formattedTime = new StringBuilder();

        appendUnit(formattedTime, years, "year");
        appendUnit(formattedTime, months, "month");
        appendUnit(formattedTime, weeks, "week");
        appendUnit(formattedTime, days, "day");
        appendUnit(formattedTime, hours, "hour");
        appendUnit(formattedTime, minutes, "minute");
        appendUnit(formattedTime, seconds, "second");

        removeTrailingComma(formattedTime);

        return formattedTime.toString();
    }

    private void appendUnit(StringBuilder builder, long value, String unit) {
        if (value > 0) {
            builder.append(value).append(' ').append(unit);
            if (value > 1) {
                builder.append('s');
            }
            builder.append(", ");
        }
    }

    private void removeTrailingComma(StringBuilder builder) {
        int length = builder.length();
        if (length >= 2 && builder.charAt(length - 2) == ',') {
            builder.replace(length - 2, length, "");
        }
    }

    @Override
    public @NotNull String getForeverString() {
        return "Forever";
    }
}
