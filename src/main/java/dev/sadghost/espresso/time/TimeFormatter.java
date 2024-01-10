package dev.sadghost.espresso.time;

import org.jetbrains.annotations.NotNull;

public interface TimeFormatter {

    @NotNull String getFormattedTime(long years, long months, long weeks, long days,
                                                     long hours, long minutes, long seconds);

    @NotNull String getForeverString();
}
