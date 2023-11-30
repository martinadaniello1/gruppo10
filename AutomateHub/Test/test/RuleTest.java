package test;


import org.junit.*;
import static org.junit.Assert.*;
import automatehub.model_view.Rule;
import automatehub.model_view.TimeTrigger;
import automatehub.model_view.AudioAction;
import java.time.Duration;


public class RuleTest {
    public static Rule rule;
    public static AudioAction a;
    public static TimeTrigger t;
    
    @Before
    public void setUp() {
        a = new AudioAction("C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav");
        t = new TimeTrigger("10:47");
        rule= new Rule("Test rule",a,t,true,Duration.ZERO);
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
        AudioAction a1= new AudioAction("C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav");
        
        rule.setAction(a1);
        assertEquals(a1,rule.getAction());
    } 
    
    @Test
    public void testGetTrigger(){
        assertEquals(t,rule.getTrigger());
    } 
    
    @Test
    public void testSetTrigger(){
        TimeTrigger t1= new TimeTrigger("10:47");
       
        rule.setTrigger(t1);
        assertEquals(t1,rule.getTrigger());
    } 
    
    @Test
    public void testGetActive(){
        assertEquals(true,rule.getActive());
        
    } 
    
    @Test
    public void testSetActive(){
        
        rule.setActive(false);
        assertEquals(false,rule.getActive());
    } 
    
}