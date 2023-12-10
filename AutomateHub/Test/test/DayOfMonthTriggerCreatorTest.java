package test;

import automatehub.model_view.trigger.Trigger;
import automatehub.model_view.trigger.DayOfMonthTrigger;
import automatehub.model_view.trigger.creator.DayOfMonthTriggerCreator;
import automatehub.model_view.*;
import org.junit.Before;
import org.junit.*;
import static org.junit.Assert.*;

public class DayOfMonthTriggerCreatorTest {

    private DayOfMonthTriggerCreator dom;

    @Before
    public void setUp() {
        dom = new DayOfMonthTriggerCreator(21);
    }

    @Test
    public void testCreate() {
        DayOfMonthTrigger domt = new DayOfMonthTrigger(21);
        Trigger triggerReturned = dom.create();
        assertEquals(domt, triggerReturned);
    }
}
