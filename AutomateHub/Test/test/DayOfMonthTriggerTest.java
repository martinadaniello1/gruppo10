package test;

import automatehub.model_view.DayOfMonthTrigger;
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

        assertEquals(dayExpected, dayOfMonth.getDayOfMonth());

    }

    @Test
    public void testGetDayOfMonth() {
        dayOfMonth.setDayOfMonth(4);
        assertEquals(dayExpected, dayOfMonth.getDayOfMonth());

    }

    @Test
    public void testSetDayOfMonth() {
        dayOfMonth.setDayOfMonth(4);
        assertEquals(dayExpected, dayOfMonth.getDayOfMonth());

    }

    @Test
    public void testCheck() {
        dayOfMonth.setDayOfMonth(LocalDate.now().getDayOfMonth());
        assertEquals(true, dayOfMonth.check());
    }
    
    @Test
    public void testCheckIsNotTrue() {
        dayOfMonth.setDayOfMonth(3);
        assertEquals(false, dayOfMonth.check());
    }

}
