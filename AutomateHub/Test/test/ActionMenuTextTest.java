package test;

import automatehub.model_view.ActionMenuText;
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

        // Test case-insensitivity
        assertEquals(ActionMenuText.PLAY, ActionMenuText.getByMenuText("pLaY aN AuDiO fIlE"));

        // Test an invalid menu text
        try {
            ActionMenuText.getByMenuText("Invalid Menu Text");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}

