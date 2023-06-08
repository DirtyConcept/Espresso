import dev.sadghost.espresso.base.Preconditions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PreconditionsTest {

    @Test
    void testCheckNotNullWithNonNullObject() {
        // Arrange
        Object obj = new Object();

        // Act and Assert
        assertDoesNotThrow(() -> Preconditions.checkNonNull(obj));
    }

    @Test
    void testCheckNotNullWithNullObject() {
        // Arrange
        Object obj = null;

        // Act and Assert
        assertThrows(NullPointerException.class, () -> Preconditions.checkNonNull(obj));
    }

    @Test
    void testCheckArgumentWithTrueCondition() {
        // Arrange
        boolean condition = true;

        // Act and Assert
        assertDoesNotThrow(() -> Preconditions.checkArgument(condition));
    }

    @Test
    void testCheckArgumentWithFalseCondition() {
        // Arrange
        boolean condition = false;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> Preconditions.checkArgument(condition));
    }
}
