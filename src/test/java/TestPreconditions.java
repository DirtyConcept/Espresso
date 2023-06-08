import dev.sadghost.ghostingly.base.Preconditions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestPreconditions {

    @Test
    void testPrecondition() {
        assertThrows(IllegalArgumentException.class, () -> Preconditions.checkArgument(false));
        assertThrows(NullPointerException.class, () -> Preconditions.checkNonNull(null));
    }
}
