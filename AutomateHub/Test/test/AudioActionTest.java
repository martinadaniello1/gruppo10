/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import automatehub.model_view.AudioAction;
import java.io.File;
import static org.junit.Assert.*;
import org.junit.*;
/**
 *
 * @author mapic
 */
public class AudioActionTest {
    
    private static AudioAction a;
    private static String filePath;
    
    @BeforeClass
    public static void SetUpClass(){
        filePath="C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav";
        a = new AudioAction("Test AudioAction",filePath);
        
    }
    
    @Test
    public void testGetFile(){
        File file = new File(filePath);
        assertEquals(file,a.getFile());
    }
    
    @Test
    public void testSetFile(){
        String filePath1="C:\\Users\\mapic\\Desktop\\Progetto\\ONE-MORE-TIME.mp3";
        File fileExp = new File(filePath1);
        a.setFile(filePath1);
        assertEquals(fileExp,a.getFile());
    }
    
    @Test
    public void testGetNameAction(){
        assertEquals("Test AudioAction",a.getNameAction());
    }
    
    @Test
    public void testSetNameAction(){
        String nameExp = "Test SetName AudioAction";
        a.setNameAction(nameExp);
        assertEquals(nameExp,a.getNameAction());
    }
    
    
}
