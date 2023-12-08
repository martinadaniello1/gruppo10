package test;

import automatehub.model_view.ExecutorFileAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import org.junit.Assert;

public class ExecutorFileActionTest {

    private ExecutorFileAction executorAction;
    private String stringToTest;
    private String filePath;

    @Before
    public void setUp() {
        filePath = "C:\\Users\\mapic\\Downloads\\esempio.py";
        executorAction = new ExecutorFileAction(filePath, new String[]{"15","17"});
    }

    @Test
    public void testExecute() throws IOException {

        //Test to verify that the execute method has not failed, checking its return value.
        executorAction.execute();
        assertEquals(0, executorAction.getExitCode());

    }
    
    @Test
    public void testExecuteShouldReturnError() {
        // Setup
        String nonExistingPath = "non/existing/path/file.txt";
        ExecutorFileAction istance = new ExecutorFileAction(nonExistingPath, new String[]{"15","17"});

         istance.execute();

        // Verify
        assertEquals(2, istance.getExitCode());
    }

    @Test
    public void testSetCommandLineArgs() {

        executorAction.setCommandLineArgs(new String[]{"2","3"});
        Assert.assertArrayEquals(new String[]{"2","3"}, executorAction.getCommandLineArgs());

    }

    @Test
    public void testGetCommandLineArgs() {

        executorAction.setCommandLineArgs(new String[]{"2","3"});
        Assert.assertArrayEquals(new String[]{"2","3"}, executorAction.getCommandLineArgs());

    }
    
    @Test
    public void testSetFilePath() {
        executorAction.setFilePath("C:\\Users\\mapic\\Downloads\\esempio.py");
        assertEquals("C:\\Users\\mapic\\Downloads\\esempio.py", executorAction.getFilePath());
    }
    
    @Test
    public void testGetFilePath() {
        executorAction.setFilePath("C:\\Users\\mapic\\Downloads\\esempio.py");
        assertEquals(filePath, executorAction.getFilePath());
    }
    
    @Test
    public void testGetParam1(){
        executorAction.setCommandLineArgs(new String[]{"2","3"});
        assertEquals("2; 3", executorAction.getParam2());
    }
    
    @Test
    public void testGetParam2(){
        assertEquals(executorAction.getFilePath(),executorAction.getParam1());
    }
}
