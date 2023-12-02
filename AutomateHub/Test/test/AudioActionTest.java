package test;

import automatehub.model_view.AudioAction;
import java.io.File;
import static org.junit.Assert.*;
import org.junit.*;

public class AudioActionTest {
    
    private static AudioAction a;
    private static String filePath;
    private static File file;
    
    @BeforeClass
    public static void SetUpClass(){
        filePath="C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav";
        file = new File(filePath);
        a = new AudioAction(filePath);        
    }
    
    @Test
    public void testAudioAction(){
        //Verifico che l'istanza non sia nulla
        assertNotNull(a);
        
        assertEquals(file,a.getFile());
        assertEquals(filePath,a.getFile().getPath());
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
    
}
