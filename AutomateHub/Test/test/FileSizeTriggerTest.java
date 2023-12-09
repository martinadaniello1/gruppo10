package test;

import automatehub.model_view.FileSizeTrigger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FileSizeTriggerTest {

    private String filePath;
    private Long specifiedSize;
    private FileSizeTrigger trigger;

    @Before
    public void setUp() {
        filePath = "./Test/test/testFiles/file_example_WAV_1MG.wav";
        specifiedSize = 1L;
        trigger = new FileSizeTrigger(filePath, specifiedSize);
    }

    @Test
    public void testCheck() {
        //Test check true
        assertTrue(trigger.check());

        //Test check false
        trigger.setSpecifiedSize(10000000L);
        assertFalse(trigger.check());
    }

    @Test
    public void testCheckFileNotFound() {
        //Non existent file
        FileSizeTrigger fileSizeTrigger = new FileSizeTrigger("nonexistent/file.txt", 1024L);

        assertFalse(fileSizeTrigger.check());
    }

    @Test
    public void testGetFilePath() {
        assertEquals(trigger.getFilePath(), filePath);
    }

    @Test
    public void testSetFilePath() {
        String filePath2 = "filePath/prova/prova.txt";
        trigger.setFilePath(filePath2);
        assertEquals(filePath2, trigger.getFilePath());
    }
    
    
    @Test
    public void testGetSpecifiedSize() {
        assertEquals(trigger.getSpecifiedSize(), specifiedSize);
    }
    
    @Test
    public void testSetSpecifiedSize() {
        Long sizeTest2 = 10000L;
        trigger.setSpecifiedSize(sizeTest2);
        assertEquals(sizeTest2, trigger.getSpecifiedSize());
    }

}
