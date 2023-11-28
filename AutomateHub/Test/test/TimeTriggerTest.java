package test;

import automatehub.model_view.TimeTrigger;
import java.time.temporal.ChronoUnit;
import org.junit.*;
import static org.junit.Assert.*;
import automatehub.model_view.TimeTrigger;
import java.time.LocalTime;



public class TimeTriggerTest {
    
    private  TimeTrigger timetrigger;
    private  static LocalTime timeExpected;
    
    
    @BeforeClass
    public static void setUpClass() {
        
        timeExpected= LocalTime.of(10, 47);
        
    }
    
    @Before
    public void setUp() {
        
        timetrigger= new TimeTrigger("10:47");
    }
   
    
    @Test
    public void testTimeTrigger() {
        
        assertNotNull(timetrigger);
        
        assertEquals(timeExpected,timetrigger.getTime());
        
        
    }
    
   
    @Test
    public void testGetTime() {
        
        
        assertEquals(timeExpected,timetrigger.getTime());
        
    }
    
    @Test
    public void testSetTime() {
        
        
        timetrigger.setTime(LocalTime.of(10, 38));
        assertEquals(LocalTime.of(10, 38),timetrigger.getTime());
        
    }
    
  
    @Test
    public void testCheck() {
               
        assertEquals(false, timetrigger.check());
        
        timetrigger.setTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));

        assertEquals(true, timetrigger.check());
    }
    
}
