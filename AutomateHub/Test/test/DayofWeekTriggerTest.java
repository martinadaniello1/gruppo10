/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test;

import automatehub.model_view.DayofWeekTrigger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class DayofWeekTriggerTest {

    private static String checkDay;
    private DayofWeekTrigger dwt;

    @BeforeClass
    public static void setUpClass() {

        checkDay = "Monday";
    }

    @Before
    public void setUp() {

        dwt = new DayofWeekTrigger("Monday");
    }

    @Test
    public void testDayofWeekTrigger() {

        assertEquals(dwt.getDayOfWeek(), DayOfWeek.valueOf(checkDay.toUpperCase()));
    }

    /**
     * Test of check method, of class DayofWeekTrigger.
     */
    @Test
    public void testCheck() {

        dwt.setDayOfWeek(LocalDate.now().getDayOfWeek());
        assertEquals(true, dwt.check());
    }

    /**
     * Test of getDayOfWeek method, of class DayofWeekTrigger.
     */
    @Test
    public void testGetDayOfWeek() {

        assertEquals(dwt.getDayOfWeek(), DayOfWeek.valueOf(checkDay.toUpperCase()));
    }

    /**
     * Test of setDayOfWeek method, of class DayofWeekTrigger.
     */
    @Test
    public void testSetDayOfWeek() {
        dwt.setDayOfWeek(DayOfWeek.SATURDAY);

        assertEquals(DayOfWeek.SATURDAY, dwt.getDayOfWeek());
    }

}
