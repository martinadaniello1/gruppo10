package test;

import automatehub.model_view.trigger.DayOfWeekTrigger;
import automatehub.model_view.trigger.DayOfWeekTriggerCreator;
import automatehub.model_view.trigger.Trigger;
import java.time.DayOfWeek;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DayOfWeekTriggerCreatorTest {

    private DayOfWeekTriggerCreator dfwCreator;
    private static DayOfWeek dayInserted;

    @BeforeClass
    public static void setUpClass() {
        dayInserted = DayOfWeek.FRIDAY;
    }

    @Before
    public void setUp() {
        dfwCreator = new DayOfWeekTriggerCreator(DayOfWeek.FRIDAY);
    }

    @Test
    public void testCreate() {
        DayOfWeekTrigger dwt = new DayOfWeekTrigger(dayInserted);
        Trigger triggerReturned = dfwCreator.create();
        assertEquals(dwt, triggerReturned);
    }

}
