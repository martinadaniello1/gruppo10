package test;

import automatehub.model_view.ActionMenuText;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionMenuTextTest {

    @Test
<<<<<<< HEAD
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

=======
    public void getMenuTextShouldReturnCorrectValues() {
        assertEquals("Play an audio file", ActionMenuText.PLAY.getMenuText());
        assertEquals("Show a message", ActionMenuText.MEX.getMenuText());
        assertEquals("Copy a file to a directory", ActionMenuText.COPY.getMenuText());
        assertEquals("Move a file from a directory", ActionMenuText.MOVE.getMenuText());
        assertEquals("Append a string at the end of a text file", ActionMenuText.APPEND.getMenuText());
        assertEquals("Remove a file from a directory", ActionMenuText.REMOVE.getMenuText());
    }

    @Test
    public void enumValuesShouldNotBeNull() {
        assertNotNull(ActionMenuText.values());
    }

    @Test
    public void enumValuesShouldHaveCorrectLength() {
        assertEquals(6, ActionMenuText.values().length);
    }

    @Test
    public void enumValuesShouldBeInExpectedOrder() {
        ActionMenuText[] expectedOrder = {
            ActionMenuText.PLAY, ActionMenuText.MEX, ActionMenuText.COPY,
            ActionMenuText.MOVE, ActionMenuText.APPEND, ActionMenuText.REMOVE
        };
        assertArrayEquals(expectedOrder, ActionMenuText.values());
    }
}
>>>>>>> task303
