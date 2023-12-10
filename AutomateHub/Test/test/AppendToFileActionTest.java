package test;

import automatehub.model_view.action.AppendToFileAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class AppendToFileActionTest {

    private AppendToFileAction appendStringAction;
    private String stringToTest;
    private String filePath;

    @Before
    public void setUp() {
        stringToTest = "Testing AppendToFileAction...";
        filePath = "./Test/test/testFiles/prova.txt";
        appendStringAction = new AppendToFileAction(stringToTest, filePath);
    }

    @Test
    public void testExecute() throws IOException {
        //Test to verify that the execute method has not failed, checking its return value.
        int result = appendStringAction.execute();
        assertEquals(0, result);

        //Test to verify that the execute method has written the correct string at the end of the file.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); //Going to the new line
            String line = reader.readLine();
            assertEquals(stringToTest, line);
        }
    }
    
    @Test
    public void testExecuteShouldReturnError(){
        // Setup
        String nonExistingPath = "non/existing/path/file.txt";
   
        AppendToFileAction notValidAction = new AppendToFileAction("", nonExistingPath);

        // Test
        int result = notValidAction.execute();

        // Verify
        assertEquals(-1, result);
    }

    @Test
    public void testSetStringToAppend() {

        appendStringAction.setStringToAppend("test message");
        assertEquals("test message", appendStringAction.getStringToAppend());

    }

    @Test
    public void testGetStringToAppend() {
        
        assertEquals(stringToTest, appendStringAction.getStringToAppend());

    }
    
    @Test
    public void testSetFilePath() {
        appendStringAction.setFilePath("C:/path/di/prova/test");
        assertEquals("C:/path/di/prova/test", appendStringAction.getFilePath());
    }
    
    @Test
    public void testGetFilePath() {
        assertEquals(filePath, appendStringAction.getFilePath());
    }
    
    @Test
    public void testGetParam1(){
        assertEquals(appendStringAction.getStringToAppend(), appendStringAction.getParam1());
    }
    
    @Test
    public void testGetParam2(){
        assertEquals(appendStringAction.getFilePath(),appendStringAction.getParam2());
    }
}
