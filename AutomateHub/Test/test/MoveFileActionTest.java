package test;

import automatehub.model_view.MoveFileAction;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;

public class MoveFileActionTest {

    @Test
    public void executeShouldMoveFileToDestinationDirectory() {
        // Setup //Modify this variables in order to test on your machine
        String startingPath = "D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory2/prova_prova.txt";
        String destinationPath = "D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory1";
        MoveFileAction moveFileAction = new MoveFileAction(startingPath, destinationPath);

        // Test
        int result = moveFileAction.execute();

        // Verify
        assertEquals(0, result);
         //Verify the presence of the file in the new directory
        File f = new File("D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory1/prova_prova.txt");
        assertEquals(true,f.exists());

    }

    @Test
    public void executeShouldReturnErrorIfSourceFileDoesNotExist() {
        // Setup
        String nonExistingPath = "non/existing/path/file.txt";
        String destinationPath = "path/to/destination/directory";
        MoveFileAction moveFileAction = new MoveFileAction(nonExistingPath, destinationPath);

        // Test
        int result = moveFileAction.execute();

        // Verify
        assertEquals(-1, result);
    }


    @Test
    public void gettersShouldReturnCorrectValues() {
        // Setup
        String startingPath = "path/to/source/file.txt";
        String destinationPath = "path/to/destination/directory";
        MoveFileAction moveFileAction = new MoveFileAction(startingPath, destinationPath);

        // Test
        assertEquals(startingPath, moveFileAction.getStartingPath());
        assertEquals(destinationPath, moveFileAction.getDestinationPath());
    }

    @Test
    public void settersShouldSetCorrectValues() {
        // Setup
        MoveFileAction moveFileAction = new MoveFileAction("", "");

        // Test
        String newStartingPath = "new/source/path/file.txt";
        String newDestinationPath = "new/destination/directory";
        moveFileAction.setStartingPath(newStartingPath);
        moveFileAction.setDestinationPath(newDestinationPath);

        // Verify
        assertEquals(newStartingPath, moveFileAction.getStartingPath());
        assertEquals(newDestinationPath, moveFileAction.getDestinationPath());
    }
}
