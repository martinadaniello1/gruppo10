package test;

import automatehub.model_view.CopyFileAction;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;

public class CopyFileActionTest {

    @Test
    public void executeShouldCopyFileToDestinationDirectory() {
        // Setup //Modify this variables in order to test on your machine
        String startingPath = "D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory1/file_example_WAV_1MG - Copia.wav";
        String destinationPath = "D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory2";
        CopyFileAction copyFileAction = new CopyFileAction(startingPath, destinationPath);

        // Test
        int result = copyFileAction.execute();

        // Verify the return of the function
        assertEquals(0, result);
        
        //Verify the presence of the file in the new directory
        File f = new File("D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory2/file_example_WAV_1MG - Copia.wav");
        assertEquals(true,f.exists());
    }

    @Test
    public void executeShouldReturnErrorIfSourceFileDoesNotExist() {
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
    public void gettersShouldReturnCorrectValues() {
        // Setup
        String startingPath = "path/to/source/file.txt";
        String destinationPath = "path/to/destination/directory";
        CopyFileAction copyFileAction = new CopyFileAction("", "");


        // Test
        assertEquals(startingPath, copyFileAction.getStartingPath());
        assertEquals(destinationPath, copyFileAction.getDestinationPath());
    }

    @Test
    public void settersShouldSetCorrectValues() {
        // Setup
        CopyFileAction copyFileAction = new CopyFileAction("", "");

        // Test
        String newStartingPath = "new/source/path/file.txt";
        String newDestinationPath = "new/destination/directory";
        copyFileAction.setStartingPath(newStartingPath);
        copyFileAction.setDestinationPath(newDestinationPath);

        // Verify
        assertEquals(newStartingPath, copyFileAction.getStartingPath());
        assertEquals(newDestinationPath, copyFileAction.getDestinationPath());
    }

    
}
