package test;

import automatehub.model_view.action.CopyFileAction;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;

public class CopyFileActionTest {
    
    private String filePathSource; 
    private String filePathDest;
    private CopyFileAction copyFileAction;

    @Before
    public void setUp(){
        filePathSource = "./Test/test/testFiles/testDirectory1/file_to_copy.jpg";
        filePathDest = "./Test/test/testFiles/testDirectory2";
        copyFileAction = new CopyFileAction(filePathSource, filePathDest);
    }
    
    @Test
    public void testExecute() {
        // Test
        int result = copyFileAction.execute();

        // Verify the return of the function
        assertEquals(0, result);
        
        //Verify the presence of the file in the new directory
        File f = new File(filePathDest + "/file_to_copy.jpg");
        assertEquals(true,f.exists());
    }

    @Test
    public void testExecuteShouldReturnError() {
        // Setup
        String nonExistingPath = "non/existing/path/file.txt";
        String destinationPath = "path/to/destination/directory";
        CopyFileAction copyFileAction = new CopyFileAction(nonExistingPath, destinationPath);

        // Test
        int result = copyFileAction.execute();

        // Verify
        assertEquals(-1, result);
    }
    
    @Test
    public void testGetStartingPath(){
        assertEquals(filePathSource, copyFileAction.getStartingPath());
    }
    
    @Test
    public void testGetDestinationPath(){
        assertEquals(filePathDest, copyFileAction.getDestinationPath());
    }
    
    @Test
    public void testGetParam1(){
        assertEquals(copyFileAction.getStartingPath(), copyFileAction.getParam1());
    }
    
    @Test
    public void testGetParam2(){
        assertEquals(copyFileAction.getDestinationPath(), copyFileAction.getParam2());
    }
    
    @Test
    public void testSetStartingPath(){
        String path = "D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory2/file_example_WAV_1MG - Copia.wav";
        copyFileAction.setStartingPath(path);
        assertEquals(path, copyFileAction.getStartingPath());
    }
    
    @Test
    public void testSetDestinationPath(){
        String path = "D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory2/file_example_WAV_1MG - Copia.wav";
        copyFileAction.setDestinationPath(path);
        assertEquals(path, copyFileAction.getDestinationPath());
    }
    
    @After
    public void resetDirectories(){
        File f = new File("./Test/test/testFiles/testDirectory2/file_to_copy.jpg");
        if(f.exists()){
            f.delete();
        }
    }
        
}