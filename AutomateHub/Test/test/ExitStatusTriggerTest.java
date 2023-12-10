package test;

import automatehub.model_view.trigger.ExitStatusTrigger;
import java.io.File;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExitStatusTriggerTest {
    private static String filePath;
    private static Integer exitCodeDesired;
    private static String[] commandLineArgs = new String[2];
    private static File f;
    private ExitStatusTrigger trigger ;
    
    @BeforeClass
    public static void setUpClass(){
        filePath="./Test/test/testFiles/example.py";
        f = new File(filePath);
        commandLineArgs[0] = "2";
        commandLineArgs[1] = "1";
        exitCodeDesired = 0;
    }
    
    @Before
    public void setUp(){
        trigger = new ExitStatusTrigger(filePath, commandLineArgs, exitCodeDesired);
    }
    
    @Test
    public void getCommandLineArgsTest(){
        assertArrayEquals(trigger.getCommandLineArgs(), commandLineArgs);
    }
    
    @Test
    public void getFilePathTest(){
        assertEquals(trigger.getFilePath(), filePath);
    }
    
    @Test
    public void getExitCodeDeired(){
        assertEquals(trigger.getExitCodeDesired(), exitCodeDesired);
    }
    
    @Test
    public void setCommandLineArgsTest(){
        String[] provaArray = {"3", "4"};
        trigger.setCommandLineArgs(provaArray);
        assertArrayEquals(trigger.getCommandLineArgs(), provaArray);
    }
    
    @Test
    public void setFilePath(){
        String pathProva = "path/di/prova";
        trigger.setFilePath(pathProva);
        assertEquals(trigger.getFilePath(), pathProva);
    }
    
    @Test
    public void setExitCodeDesired(){
        Integer provaInt = 5;
        trigger.setExitCodeDesired(provaInt);
        assertEquals(trigger.getExitCodeDesired(), provaInt);
    }
    
    @Test
    public void getParam1Test(){
        assertEquals(trigger.getParam1(), trigger.getFilePath());
    }
    
    @Test
    public void getParam2Test(){
        assertEquals(trigger.getParam2(), String.join(";", trigger.getCommandLineArgs()));
    }
    
    @Test
    public void getParam3Test(){
        assertEquals(trigger.getParam3(), trigger.getExitCodeDesired().toString());
    }
    
    @Test
    public void checkTestIfFileNotExist(){
        ExitStatusTrigger t = new ExitStatusTrigger("./fileNonEsistenteSicuramenteLoGiuro", commandLineArgs, exitCodeDesired );
        assertFalse(t.check());
    }
    
    @Test
    public void checkTestIfFileExistAndReturnTrue(){
        assertTrue(trigger.check());
    }
}
