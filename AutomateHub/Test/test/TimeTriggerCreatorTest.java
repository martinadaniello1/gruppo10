package test;

import automatehub.model_view.TimeTrigger;
import automatehub.model_view.TimeTriggerCreator;
import automatehub.model_view.Trigger;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.*;


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
