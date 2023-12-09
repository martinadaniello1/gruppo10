package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import automatehub.model_view.DialogBoxAction;

public class DialogBoxActionTest {

    private DialogBoxAction dialogAction;
    private String expectedMessage;

    @Before
    public void setUp() {
        expectedMessage = "testing action";
        dialogAction = new DialogBoxAction(expectedMessage);
    }

    @Test
    public void testDialogBoxAction() {
        assertEquals(expectedMessage, dialogAction.getMessage());
    }

    @Test
    public void testSetMessage() {
        dialogAction.setMessage("test message");
        assertEquals("test message", dialogAction.getMessage());
    }

    @Test
    public void testGetMessage() {
        assertEquals(expectedMessage, dialogAction.getMessage());
    }

    @Test
    public void testGetParam1() {
        assertEquals(dialogAction.getMessage(), dialogAction.getParam1());
    }

    @Test
    public void testGetParam2() {
        assertEquals("", dialogAction.getParam2());
    }
}
