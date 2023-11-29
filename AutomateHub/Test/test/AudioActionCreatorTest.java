/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import automatehub.model_view.Action;
import automatehub.model_view.AudioAction;
import automatehub.model_view.AudioActionCreator;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mapic
 */
public class AudioActionCreatorTest {

    private static String pathFile;
    private static AudioActionCreator ac;
    
    @BeforeClass
    public static void SetUpClass(){
        pathFile="C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav";
         ac = new AudioActionCreator(pathFile);        
    }
    
    @Test
    public void createTest(){
        AudioAction a = new AudioAction(pathFile);
        Action acReturned = ac.create();
        assertTrue(a.equals(acReturned));
        
        AudioAction aFalse = new AudioAction("11:21");
        assertFalse(aFalse.equals(acReturned));
    }
}
