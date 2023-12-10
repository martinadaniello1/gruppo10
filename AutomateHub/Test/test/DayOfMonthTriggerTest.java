package test;

import automatehub.model_view.trigger.DayOfMonthTrigger;
import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DayOfMonthTriggerTest {

    private DayOfMonthTrigger dayOfMonth;
    private Integer dayExpected;

    @Before
    public void setUp() {
        dayOfMonth = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth());
        dayExpected = LocalDate.now().getDayOfMonth();
    }

    @Test
    public void testDayOfMonthTrigger() {
        assertNotNull(dayOfMonth);
        assertTrue(dayOfMonth instanceof DayOfMonthTrigger);

    }

    @Test
    public void testGetDayOfMonth() {
        assertEquals(dayExpected, dayOfMonth.getDayOfMonth());

    }

    @Test
    public void testSetDayOfMonth() {
        Integer test = 4 ;
        dayOfMonth.setDayOfMonth(test);
        assertEquals(test , dayOfMonth.getDayOfMonth());
    }

    @Test
    public void testCheck() {
        dayOfMonth.setDayOfMonth(LocalDate.now().getDayOfMonth());
        assertEquals(true, dayOfMonth.check());
    }
    
    @Test
    public void testCheckIsNotTrue() {
        dayOfMonth.setDayOfMonth(LocalDate.now().getDayOfMonth() + 1);
        assertEquals(false, dayOfMonth.check());
    }

}
