/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class TimeTriggerTest {
    
    private static TimeTrigger timetrigger;
    
    @BeforeClass
    public static void setUpClass() {
        
        timetrigger= new TimeTrigger("2023/11/22/10:47");
    }
    
    @Test
    public void testFalsecheckTime() {
        timetrigger.check();        
        assertEquals(false, timetrigger.isVerified());
    }
    
    @Test
    public void testTruecheckTime() {
        
        timetrigger.setTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        timetrigger.checkTime();
        
        assertEquals(true, timetrigger.isVerified());
    }
    
}
