package test;

import automatehub.model_view.DayOfWeekTrigger;
import automatehub.model_view.DayOfWeekTriggerCreator;
import automatehub.model_view.Trigger;
import java.time.DayOfWeek;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DayOfWeekTriggerCreatorTest {

    private DayOfWeekTriggerCreator dfwCreator;
    private static String dayInserted;

    @BeforeClass
    public static void setUpClass() {
        dayInserted = "Friday";
    }

    @Before
    public void setUp() {

        dfwCreator = new DayOfWeekTriggerCreator("Friday");
    }

    /**
     * Test of class DayOfWeekTriggerCreator.
     */
    @Test
    public void DayofWeekTriggerCreatorTest() {

        assertEquals(dfwCreator.getDayInserted(), dayInserted);
    }

    @Test
    public void testCreate() {

        DayOfWeekTrigger dwt = new DayOfWeekTrigger("Friday");
        Trigger triggerReturned = dfwCreator.create();
        assertEquals(dwt, triggerReturned);
    }

    @Test
    public void testGetDayInserted() {

        assertEquals(dfwCreator.getDayInserted(), dayInserted); 
    }

    @Test
    public void testSetDayInserted() {

        dfwCreator.setDayInserted("Tuesday");
        assertEquals(DayOfWeek.TUESDAY, DayOfWeek.valueOf(dfwCreator.getDayInserted().toUpperCase()));
    }

}
