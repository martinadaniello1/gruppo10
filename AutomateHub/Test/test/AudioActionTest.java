/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test;

import automatehub.model_view.AudioAction;
import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author mapic
 */
public class AudioActionTest {
    
    public AudioAction audio;
    public File fileExp;
    
    @Before
    public void setUp() {
        String filePath = "C:\\Users\\mapic\\Desktop\\Progetto\\ONE MORE TIME.wav";
        File  fileExp = new File(filePath);
        AudioAction audio = new AudioAction("Test AudioAction", filePath);
    }

    @Test
    public void testExecute() {
        assertEquals(0, audio.execute());
    }
    
    @Test
    public void testAudioActionWithInvalidFile() {
        // Testa l'esecuzione con un file audio non valido
        String invalidFilePath = "C:\\Users\\mapic\\Desktop\\Progetto\\audio.txt"; 
        AudioAction audioAction = new AudioAction("PlayAudio", invalidFilePath);

        // Verifica che l'esecuzione non abbia avuto successo (ritorno -1)
        assertEquals(-1, audioAction.execute());
    }

    /**
     * Test of getNameAction method, of class AudioAction.
     */
    @Test
    public void testGetNameAction() {
        
        
        assertEquals("Test AudioAction",audio.getNameAction());
    }
    
    
}
