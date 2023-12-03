package test;

import automatehub.model_view.AppendToFileAction;
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
        stringToTest = "prova";
        filePath = "C:\\Users\\marti\\OneDrive\\Desktop\\prova.txt";
        appendStringAction = new AppendToFileAction(stringToTest, filePath);
    }

    @Test
    public void testExecute() throws IOException {

        //Test to verify that the execute method has not failed, checking its return value.
        int result = appendStringAction.execute();
        assertEquals(0, result);

        //Test to verify that the execute method has written the correct string at the end of the file.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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

        appendStringAction.setStringToAppend("prova");
        assertEquals(stringToTest, appendStringAction.getStringToAppend());

    }
    
    @Test
    public void testSetFilePath() {
        appendStringAction.setFilePath("C:\\Users\\marti\\OneDrive\\Desktop\\file.txt");
        assertEquals("C:\\Users\\marti\\OneDrive\\Desktop\\file.txt", appendStringAction.getFilePath());
    }
    
    @Test
    public void testGetFilePath() {
        appendStringAction.setFilePath("C:\\Users\\marti\\OneDrive\\Desktop\\prova.txt");
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
