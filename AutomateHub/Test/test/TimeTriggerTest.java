package test;

import automatehub.model_view.TimeTrigger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.*;
import static org.junit.Assert.*;
import automatehub.model_view.TimeTrigger;
import java.time.format.DateTimeFormatter;

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
    
    public void testgetTime() {
        
        String triggertime= "2023/11/22/10:47";
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm");
        LocalDateTime triggertimeparse= LocalDateTime.parse(triggertime,dtf).truncatedTo(ChronoUnit.MINUTES);   
        
        assertEquals(triggertimeparse,timetrigger.getTime());
    }
    
    @Test
    public void testFalsecheck() {
               
        assertEquals(false, timetrigger.check());
    }
    
    @Test
    public void testTruecheck() {
        
        timetrigger.setTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
   //     timetrigger.checkTime();

        assertEquals(true, timetrigger.check());
    }
    
}
