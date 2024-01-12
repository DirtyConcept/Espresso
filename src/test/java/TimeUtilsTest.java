import dev.sadghost.espresso.time.TimeUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TimeUtilsTest {

    @Test
    void getCurrentDate() {
        String currentDate = TimeUtils.getCurrentDate("yyyy-MM-dd");
        assertNotNull(currentDate);
    }

    @Test
    void parseTime() {
        long timeDuration = TimeUtils.parseTime("1y2M");
        assertEquals(TimeUtils.MILLISECONDS_IN_YEAR + 2 * TimeUtils.MILLISECONDS_IN_MONTH, timeDuration);
    }

    @Test
    void formatTime() {
        long duration = 365 * TimeUtils.MILLISECONDS_IN_DAY + 2 * TimeUtils.MILLISECONDS_IN_MONTH;
        String formattedTime = TimeUtils.formatTime(duration, TimeUnit.MILLISECONDS);
        assertNotNull(formattedTime);
        assertEquals("1 year, 2 months", formattedTime);
    }

    @Test
    void formatSeconds() {
        String formattedSeconds = TimeUtils.formatSeconds(300);
        assertEquals("05:00", formattedSeconds);
    }

    @Test
    void testDummyTimeFormatter() {
        DummyTimeFormatter dummyTimeFormatter = new DummyTimeFormatter();
        String formattedTime = dummyTimeFormatter.getFormattedTime(0, 0, 0, 0, 4, 22, 0);
        assertNotNull(formattedTime);
        assertEquals("4 hours 22 minutes", formattedTime);

        String foreverString = dummyTimeFormatter.getForeverString();
        assertNotNull(foreverString);
        assertEquals("Forever", foreverString);
    }
}