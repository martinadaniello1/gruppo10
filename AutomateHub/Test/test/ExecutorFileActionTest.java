package test;

import automatehub.model_view.action.ExecutorFileAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import org.junit.Assert;

public class ExecutorFileActionTest {

    private ExecutorFileAction executorAction;
    private String[] commandLineArgs;
    private String filePath;

    @Before
    public void setUp() {
        filePath = "./Test/test/testFiles/example.py";
        commandLineArgs = new String[]{"15","17"};
        executorAction = new ExecutorFileAction(filePath, commandLineArgs);
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

        // Verify //In Python, an exit code with a value of two indicates that an error occurred during execution.
        assertEquals(2, istance.getExitCode());
    }

    @Test
    public void testSetCommandLineArgs() {

        executorAction.setCommandLineArgs(new String[]{"2","3"});
        Assert.assertArrayEquals(new String[]{"2","3"}, executorAction.getCommandLineArgs());

    }

    @Test
    public void testGetCommandLineArgs() {

        Assert.assertArrayEquals( commandLineArgs, executorAction.getCommandLineArgs());

    }
    
    @Test
    public void testSetFilePath() {
        executorAction.setFilePath("testing/to/set/file/path");
        assertEquals("testing/to/set/file/path", executorAction.getFilePath());
    }
    
    @Test
    public void testGetFilePath() {
        assertEquals(filePath, executorAction.getFilePath());
    }
    
    @Test
    public void testGetParam1(){
        assertEquals(String.join("; ", commandLineArgs), executorAction.getParam2());
    }
    
    @Test
    public void testGetParam2(){
        assertEquals(executorAction.getFilePath(),executorAction.getParam1());
    }
}
