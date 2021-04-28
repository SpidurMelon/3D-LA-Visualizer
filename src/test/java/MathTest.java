import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import opengl.util.MathUtils;


public class MathTest {

    @Test
    public void clampTest() {
        assertEquals(0,  MathUtils.clamp(0,-1,1));
        assertEquals(1,  MathUtils.clamp(0,2,1));
        assertEquals(0.5,MathUtils.clamp(0,0.5,1));
        assertNotEquals(0.5, MathUtils.clamp(0.5, 0.6, 0.7));
    }
}
