package test;

import automatehub.model_view.CurrentDayTrigger;
import automatehub.model_view.CurrentDayTriggerCreator;
import automatehub.model_view.Trigger;
import java.time.LocalDate;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CurrentDayTriggerCreatorTest {
    private LocalDate date;
    private CurrentDayTriggerCreator cdtc;
    
    @Before
    public void setUp(){
        date = LocalDate.now();
        cdtc= new CurrentDayTriggerCreator(date);
    }
    
    @Test
    public void testCreate(){
        CurrentDayTrigger cdt = new CurrentDayTrigger(date);
        Trigger t = cdtc.create();
        assertTrue(t.equals(cdt));
    }
}
