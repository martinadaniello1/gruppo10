/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.*;
import static org.junit.Assert.*;
import automatehub.model_view.TimeTrigger;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Luca
 */
public class TimeTriggerTest {
    
    private  TimeTrigger timetrigger;
    private  LocalDateTime dateExpected;
    private  String nameExpected="";
    private  String falseNameExpected="";
    private  LocalDateTime falseDateExpected;
    
    @Before
    public  void setUp() {
        
        timetrigger= new TimeTrigger("2023/11/22/10:47", "Testing Trigger");
        dateExpected= LocalDateTime.of(2023, 11, 22, 10, 47);
        nameExpected= "Testing Trigger";
        falseNameExpected="False Testing Trigger";
        falseDateExpected= LocalDateTime.of(2023, 06, 22, 10, 10);
        
    }
    
    @Test
    public void testTimeTrigger() {
        
        
        
        assertEquals(dateExpected,timetrigger.getTime());
        assertNotEquals(falseDateExpected,timetrigger.getTime());
        
        assertEquals(nameExpected, timetrigger.getNameTrigger());
        assertNotEquals(falseNameExpected,timetrigger.getNameTrigger());
        
    }
    
   
    
    @Test
    public void testgetTime() {
        
        timetrigger.setTime(LocalDateTime.of(2023, 06, 22, 10, 10));
        assertEquals(falseDateExpected,timetrigger.getTime());
        assertNotEquals(dateExpected,timetrigger.getTime());
    }
    
    @Test
    public void testsetTime() {
        
        
        timetrigger.setTime(LocalDateTime.of(2023,10, 23, 10, 38));
        assertEquals(LocalDateTime.of(2023,10, 23, 10, 38),timetrigger.getTime());
        assertNotEquals(falseDateExpected,timetrigger.getTime());
    }
    
    @Test
    public void testgetNameTrigger() {
        
        timetrigger.setNameTrigger("False Testing Trigger");
        assertEquals(falseNameExpected, timetrigger.getNameTrigger());
        assertNotEquals(nameExpected,timetrigger.getNameTrigger());
        
    }
    
    @Test
    public void testsetNameTrigger() {
        
        timetrigger.setNameTrigger("Changed name");
        assertEquals("Changed name",timetrigger.getNameTrigger());
        assertNotEquals(falseNameExpected,timetrigger.getNameTrigger());
        
    }
    
    @Test
    public void testcheck() {
               
        assertEquals(false, timetrigger.check());
        
        timetrigger.setTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(true, timetrigger.check());
    }
    
}
