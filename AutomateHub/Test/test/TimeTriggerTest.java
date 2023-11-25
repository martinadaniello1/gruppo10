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
    
    private  TimeTrigger timetrigger;
    private  static LocalDateTime dateExpected;
    private  static String nameExpected="";
    
    
    
    @BeforeClass
    public static void setUpClass() {
        
        dateExpected= LocalDateTime.of(2023, 11, 22, 10, 47);
        nameExpected= "Testing Trigger";
        
    }
    
    @Before
    public void setUp() {
        
        timetrigger= new TimeTrigger("2023/11/22/10:47", "Testing Trigger");
    }
   
    
    @Test
    public void testTimeTrigger() {
        
        assertNotNull(timetrigger);
        
        assertEquals(dateExpected,timetrigger.getTime());
        
        assertEquals(nameExpected, timetrigger.getNameTrigger());
        
        
    }
    
   
    @Test
    public void testGetTime() {
        
        
        assertEquals(dateExpected,timetrigger.getTime());
        
    }
    
    @Test
    public void testSetTime() {
        
        
        timetrigger.setTime(LocalDateTime.of(2023,10, 23, 10, 38));
        assertEquals(LocalDateTime.of(2023,10, 23, 10, 38),timetrigger.getTime());
        
    }
    
    @Test
    public void testGetNameTrigger() {
        
        
        assertEquals(nameExpected, timetrigger.getNameTrigger());
        
        
    }
    
    @Test
    public void testSetNameTrigger() {
        
        timetrigger.setNameTrigger("Changed name");
        assertEquals("Changed name",timetrigger.getNameTrigger());
       
        
    }
    
    @Test
    public void testCheck() {
               
        assertEquals(false, timetrigger.check());
        
        timetrigger.setTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        assertEquals(true, timetrigger.check());
    }
    
}
