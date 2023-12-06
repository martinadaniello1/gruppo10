package test;

import automatehub.model_view.TriggerMenuText;
import static org.junit.Assert.*;
import org.junit.Test;

public class TriggerMenuTextTest {

    @Test
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

}
