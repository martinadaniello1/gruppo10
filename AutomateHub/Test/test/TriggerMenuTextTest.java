package test;

import automatehub.model_view.TriggerMenuText;
<<<<<<< HEAD
import static org.junit.Assert.*;
import org.junit.Test;
=======
import org.junit.Test;
import static org.junit.Assert.*;
>>>>>>> task303

public class TriggerMenuTextTest {

    @Test
<<<<<<< HEAD
    public void testGetByMenuText() {
        assertEquals(TriggerMenuText.TIME, TriggerMenuText.getByMenuText("When the clock hits ..."));
        assertEquals(TriggerMenuText.DAYMONTH, TriggerMenuText.getByMenuText("When it is this day of the month ..."));

        //Test case-insensitivity
        assertEquals(TriggerMenuText.TIME, TriggerMenuText.getByMenuText("WHEn tHe clOCk hiTs ..."));

        try {
            TriggerMenuText.getByMenuText("Invalid Menu Text");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }

    }

=======
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
>>>>>>> task303
}
