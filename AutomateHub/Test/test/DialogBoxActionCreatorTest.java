/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import automatehub.model_view.Action;
import automatehub.model_view.DialogBoxAction;
import automatehub.model_view.DialogBoxActionCreator;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mapic
 */
public class DialogBoxActionCreatorTest {
   

    private static String pathFile;
    private static DialogBoxActionCreator ac;
    
    @BeforeClass
    public static void SetUpClass(){
        pathFile="C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav";
         ac = new DialogBoxActionCreator(pathFile);        
    }
    
    @Test
    public void createTest(){
        DialogBoxAction a = new DialogBoxAction(pathFile);
        Action acReturned = ac.create();
        assertTrue(a.equals(acReturned));
        
    }
}
