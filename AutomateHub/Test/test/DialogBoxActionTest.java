/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import automatehub.model_view.DialogBoxAction;

/**
 *
 * @author martinadaniello
 */
public class DialogBoxActionTest {
    
    private DialogBoxAction dialogAction;
    private String expectedActionName;
    private String expectedMessage;
    
    @Before
    public void setUp() {
        expectedActionName = "Test Dialog Box Action";
        expectedMessage = "testing action";
        dialogAction = new DialogBoxAction(expectedActionName, expectedMessage);
    }
    
    @Test
    public void testDialogBoxAction() {
        assertEquals(expectedActionName, dialogAction.getNameAction());
        assertEquals(expectedMessage, dialogAction.getMessage());
    }
    
 /*   @Test
    public void testExecute() {
        
        
    }*/
    
    @Test
    public void setNameAction() {
        dialogAction.setNameAction("test name");
        assertEquals("test name", dialogAction.getNameAction());
    }
    
    @Test
    public void getNameAction() {
        dialogAction.setNameAction("Test Dialog Box Action");
        assertEquals(expectedActionName, dialogAction.getNameAction());
    }
    
    @Test
    public void setMessage() {
        dialogAction.setMessage("test message");
        assertEquals("test message", dialogAction.getMessage());
    }
    
    @Test
    public void getMessage() {
        dialogAction.setMessage("testing action");
        assertEquals(expectedMessage, dialogAction.getMessage());
    }
}
