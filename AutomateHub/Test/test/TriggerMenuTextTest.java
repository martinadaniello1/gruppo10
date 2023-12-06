package test;

import automatehub.model_view.TriggerMenuText;
import org.junit.Test;
import static org.junit.Assert.*;

public class TriggerMenuTextTest {

    @Test
    public void getMenuTextShouldReturnCorrectValue() {
        assertEquals("When the clock hits ...", TriggerMenuText.TIME.getMenuText());
    }

    @Test
    public void enumValuesShouldNotBeNull() {
        assertNotNull(TriggerMenuText.values());
    }

    @Test
    public void enumValuesShouldHaveCorrectLength() {
        assertEquals(1, TriggerMenuText.values().length);
    }

    @Test
    public void enumValuesShouldBeInExpectedOrder() {
        TriggerMenuText[] expectedOrder = { TriggerMenuText.TIME };
        assertArrayEquals(expectedOrder, TriggerMenuText.values());
    }
}
