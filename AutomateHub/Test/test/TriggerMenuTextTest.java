package test;

import automatehub.model_view.trigger.TriggerMenuText;
import static org.junit.Assert.*;
import org.junit.Test;

public class TriggerMenuTextTest {

    @Test
    public void testGetByMenuText() {
        assertEquals(TriggerMenuText.TIME, TriggerMenuText.getByMenuText("When the clock hits..."));
        assertEquals(TriggerMenuText.DAYMONTH, TriggerMenuText.getByMenuText("When it is this day of the month..."));
        assertEquals(TriggerMenuText.CURRENTDAY, TriggerMenuText.getByMenuText("When it is this day..."));
        assertEquals(TriggerMenuText.FILESIZE, TriggerMenuText.getByMenuText("When this file's size is bigger than this value..."));
        assertEquals(TriggerMenuText.EXIT, TriggerMenuText.getByMenuText("When the program returns..."));
        assertEquals(TriggerMenuText.DAYWEEK, TriggerMenuText.getByMenuText("When it is this day of the week..."));
        assertEquals(TriggerMenuText.FINDFILE, TriggerMenuText.getByMenuText("Found a file in a directory..."));
    }

    @Test
    public void testGetByMenuTextCaseInsensitivity() {
        //Test case-insensitivity
        assertEquals(TriggerMenuText.TIME, TriggerMenuText.getByMenuText("WHEn tHe clOCk hiTs..."));
    }

    @Test
    public void testGetByInvalidMenuText() {
        try {
            TriggerMenuText.getByMenuText("Invalid Menu Text");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testGetMenuTextShouldReturnCorrectValue() {
        assertEquals("When the clock hits...", TriggerMenuText.TIME.getMenuText());
    }

    @Test
    public void testEnumValuesShouldNotBeNull() {
        assertNotNull(TriggerMenuText.values());
    }

    @Test
    public void testEnumValuesShouldHaveCorrectLength() {
        assertEquals(7, TriggerMenuText.values().length);
    }

}
