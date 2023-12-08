/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test;

import automatehub.model_view.DayofWeekTrigger;
import automatehub.model_view.DayofWeekTriggerCreator;
import automatehub.model_view.Trigger;
import java.time.DayOfWeek;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Luca
 */
public class DayofWeekTriggerCreatorTest {

    private DayofWeekTriggerCreator dfwCreator;
    private static String dayInserted;

    @BeforeClass
    public static void setUpClass() {
        dayInserted = "Friday";
    }

    @Before
    public void setUp() {

        dfwCreator = new DayofWeekTriggerCreator("Friday");
    }

    /**
     * Test of class DayofWeekTriggerCreator.
     */
    @Test
    public void DayofWeekTriggerCreatorTest() {

        assertEquals(dfwCreator.getDayInserted(), dayInserted);
    }

    @Test
    public void testCreate() {

        DayofWeekTrigger dwt = new DayofWeekTrigger("Friday");
        Trigger triggerReturned = dfwCreator.create();
        assertEquals(dwt, triggerReturned);
    }

    @Test
    public void testgetDayInserted() {

        assertEquals(dfwCreator.getDayInserted(), dayInserted);
    }

    @Test
    public void testsetDayInserted() {

        dfwCreator.setDayInserted("Tuesday");
        assertEquals(DayOfWeek.TUESDAY, DayOfWeek.valueOf(dfwCreator.getDayInserted().toUpperCase()));
    }

}
