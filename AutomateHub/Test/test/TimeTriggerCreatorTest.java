/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import automatehub.model_view.TimeTrigger;
import automatehub.model_view.TimeTriggerCreator;
import automatehub.model_view.Trigger;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.*;

/**
 *
 * @author mapic
 */
public class TimeTriggerCreatorTest {
    private static TimeTriggerCreator ac;
    
    @BeforeClass
    public static void SetUpClass(){
         ac = new TimeTriggerCreator("11:21");        
    }
    
    @Test
    public void createTest(){
        TimeTrigger a = new TimeTrigger("11:21");
        Trigger acReturned = ac.create();
        assertTrue(a.equals(acReturned));
        
    }
}
