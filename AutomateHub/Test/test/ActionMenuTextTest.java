package test;

import automatehub.model_view.action.ActionMenuText;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionMenuTextTest {

    @Test
    public void testGetByMenuText() {
        // Test each enum constant
        assertEquals(ActionMenuText.PLAY, ActionMenuText.getByMenuText("Play an audio file"));
        assertEquals(ActionMenuText.MEX, ActionMenuText.getByMenuText("Show a message"));
        assertEquals(ActionMenuText.COPY, ActionMenuText.getByMenuText("Copy a file to a directory"));
        assertEquals(ActionMenuText.MOVE, ActionMenuText.getByMenuText("Move a file from a directory"));
        assertEquals(ActionMenuText.APPEND, ActionMenuText.getByMenuText("Append a string at the end of a text file"));
        assertEquals(ActionMenuText.REMOVE, ActionMenuText.getByMenuText("Remove a file from a directory"));
        assertEquals(ActionMenuText.EXECUTE, ActionMenuText.getByMenuText("Execute external program"));
    }

    @Test
    public void testGetMenuTextShouldReturnCorrectValues() {
        assertEquals("Play an audio file", ActionMenuText.PLAY.getMenuText());
        assertEquals("Show a message", ActionMenuText.MEX.getMenuText());
        assertEquals("Copy a file to a directory", ActionMenuText.COPY.getMenuText());
        assertEquals("Move a file from a directory", ActionMenuText.MOVE.getMenuText());
        assertEquals("Append a string at the end of a text file", ActionMenuText.APPEND.getMenuText());
        assertEquals("Remove a file from a directory", ActionMenuText.REMOVE.getMenuText());
    }

    @Test
    public void testGetMenuTextCaseInsensitivity() {
        assertEquals(ActionMenuText.PLAY, ActionMenuText.getByMenuText("pLaY aN AuDiO fIlE"));
    }

    @Test
    public void testGetInvalidMenuText() {
        // Test an invalid menu text
        try {
            ActionMenuText.getByMenuText("Invalid Menu Text");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testEnumValuesShouldNotBeNull() {
        assertNotNull(ActionMenuText.values());
    }

    @Test
    public void testEnumValuesShouldHaveCorrectLength() {
        assertEquals(7, ActionMenuText.values().length);
    }

}
