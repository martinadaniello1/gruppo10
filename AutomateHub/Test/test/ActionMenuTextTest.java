package test;

import automatehub.model_view.ActionMenuText;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionMenuTextTest {

    @Test
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
