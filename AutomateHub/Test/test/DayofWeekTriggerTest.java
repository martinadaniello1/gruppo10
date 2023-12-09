package test;

import automatehub.model_view.DayOfWeekTrigger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DayOfWeekTriggerTest {

    private static String checkDay;
    private DayOfWeekTrigger dwt;

    @BeforeClass
    public static void setUpClass() {
        checkDay = "Monday";
    }

    @Before
    public void setUp() {
        dwt = new DayOfWeekTrigger(DayOfWeek.valueOf("Monday".toUpperCase()));
    }

    @Test
    public void testDayofWeekTrigger() {
        assertEquals(dwt.getDayOfWeek(), DayOfWeek.valueOf(checkDay.toUpperCase()));
    }

    /**
     * Test of check method, of class DayOfWeekTrigger.
     */
    @Test
    public void testCheck() {
        dwt.setDayOfWeek(LocalDate.now().getDayOfWeek());
        assertEquals(true, dwt.check());
    }

    /**
     * Test of getDayOfWeek method, of class DayOfWeekTrigger.
     */
    @Test
    public void testGetDayOfWeek() {
        assertEquals(dwt.getDayOfWeek(), DayOfWeek.valueOf(checkDay.toUpperCase()));
    }

    /**
     * Test of setDayOfWeek method, of class DayOfWeekTrigger.
     */
    @Test
    public void testSetDayOfWeek() {
        dwt.setDayOfWeek(DayOfWeek.SATURDAY);
        assertEquals(DayOfWeek.SATURDAY, dwt.getDayOfWeek());
    }

}
