/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;


import org.junit.*;
import static org.junit.Assert.*;
import automatehub.model_view.Rule;
import automatehub.model_view.TimeTrigger;
import automatehub.model_view.AudioAction;

/**
 *
 * @author mapic
 */
public class RuleTest {
    public static Rule rule;
    public static AudioAction a;
    public static TimeTrigger t;
    
    @Before
    public void setUp() {
        a = new AudioAction("Test rule action",
                "C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav");
        t = new TimeTrigger("2023/11/22/10:47","Test rule trigger");
        rule= new Rule("Test rule",a,t,true);
    }
        
    @Test
    public void testRule(){
        //Verifico che l'istanza non sia nulla
        assertNotNull(rule);
        
        assertEquals(a,rule.getAction());
        assertEquals(t,rule.getTrigger());
        assertEquals("Test rule",rule.getNameRule());
        assertEquals(true,rule.getActive());
    }
    
    @Test 
    public void testGetNameRule(){
        assertEquals("Test rule",rule.getNameRule());
    }
    
    @Test
    public void testSetNameRule(){
        String nameExp="Test Set rule";
        rule.setNameRule(nameExp);
        assertEquals(nameExp,rule.getNameRule());
    }
    
    @Test
    public void testGetAction(){
        rule.setAction(a);
        assertEquals(a,rule.getAction());
    } 
    
    @Test
    public void testSetAction(){
        AudioAction a1= new AudioAction("Test rule SetAction",
                "C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav");
        assertNotEquals(a1,rule.getAction());
        rule.setAction(a1);
        assertEquals(a1,rule.getAction());
    } 
    
    @Test
    public void testGetTrigger(){
        assertEquals(t,rule.getTrigger());
    } 
    
    @Test
    public void testSetTrigger(){
        TimeTrigger t1= new TimeTrigger("2023/11/24/10:47","Test");
        assertNotEquals(t1,rule.getTrigger());
        rule.setTrigger(t1);
        assertEquals(t1,rule.getTrigger());
    } 
    
    @Test
    public void testGetActive(){
        assertEquals(true,rule.getActive());
        rule.setActive(false);
        assertEquals(false,rule.getActive());
    } 
    
    @Test
    public void testSetActive(){
        rule.setActive(true);
        assertEquals(true,rule.getActive());
        rule.setActive(false);
        assertEquals(false,rule.getActive());
    } 
    
}
