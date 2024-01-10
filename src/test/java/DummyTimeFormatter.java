import dev.sadghost.espresso.time.TimeFormatter;
import org.jetbrains.annotations.NotNull;

class DummyTimeFormatter implements TimeFormatter {
    @Override
    public @NotNull String getFormattedTime(long years, long months, long weeks, long days, long hours, long minutes, long seconds) {
        return String.format("%d hours %d minutes", hours, minutes);
    }

    @Override
    public @NotNull String getForeverString() {
        return "Forever";
    }
}
