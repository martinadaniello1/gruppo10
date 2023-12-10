package test;

import automatehub.model_view.trigger.CurrentDayTrigger;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CurrentDayTriggerTest {
    private LocalDate date;
    private CurrentDayTrigger trigger;
    
    @Before
    public void setUp(){
        date = LocalDate.now();
        trigger = new CurrentDayTrigger(date);
    }
    
    @Test
    public void testGetDate(){
        assertEquals(date,trigger.getDate());
    }
    
    @Test
    public void testSetDate(){
        LocalDate testDate = LocalDate.of(2023,11,05);
        trigger.setDate(testDate);
        assertEquals(trigger.getDate(), testDate);
    }
    
    @Test
    public void testCheck(){
        trigger.setDate(LocalDate.of(1900,1,1));
        assertEquals(false, trigger.check());
        trigger.setDate(LocalDate.now());
        assertEquals(true, trigger.check());
    }
}
