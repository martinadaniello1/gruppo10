package test;

import automatehub.model_view.TimeTrigger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class TimeTriggerTest {
    
    private static TimeTrigger timetrigger;
    
    @BeforeClass
    public static void setUpClass() {
        
        timetrigger= new TimeTrigger("2023/11/22/10:47");
    }
    
    @Test
    public void testFalsecheckTime() {
        timetrigger.check();        
        assertEquals(false, timetrigger.check());
    }
    
    @Test
    public void testTruecheckTime() {
        
        timetrigger.setTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
   //     timetrigger.checkTime();
        
        assertEquals(true, timetrigger.check());
    }
    
}
