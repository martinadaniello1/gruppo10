package test;

import automatehub.model_view.MoveFileAction;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;

public class MoveFileActionTest {

    private String startingPath;
    private String destinationPath;
    private MoveFileAction moveFileAction;

    @Before
    public void setUp() {
        startingPath = "D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory2/prova_prova.txt";
        destinationPath = "D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory1";
        moveFileAction = new MoveFileAction(startingPath, destinationPath);
    }

    @Test
    public void testExecuteShouldMoveFileToDestinationDirectory() {

        int result = moveFileAction.execute();

        assertEquals(0, result);
        //Verify the presence of the file in the new directory
        File f = new File("D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory1/prova_prova.txt");
        assertEquals(true, f.exists());

    }

    @Test
    public void testExecuteShouldReturnErrorIfSourceFileDoesNotExist() {
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
    public void testGetStartingPath() {
        moveFileAction.setDestinationPath("D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory2/prova_prova.txt");
        assertEquals(destinationPath, moveFileAction.getDestinationPath());
    }

    @Test
    public void testSetStartingPath() {
        moveFileAction.setStartingPath("startingPath");
        assertEquals("startingPath", moveFileAction.getStartingPath());

    }

    @Test
    public void testGetDestinationPath() {
        moveFileAction.setDestinationPath("D:/Unisa/Magistrale/1--1/Software Engineering/progetto/provaDirectory1");
        assertEquals(destinationPath, moveFileAction.getDestinationPath());
    }

    @Test
    public void testSetDestinationPath() {
        moveFileAction.setDestinationPath("destinationPath");
        assertEquals("destinationPath", moveFileAction.getDestinationPath());
    }

    @Test
    public void testGetParam1() {
        assertEquals(moveFileAction.getStartingPath(), moveFileAction.getParam1());
    }

    @Test
    public void testGetParam2() {
        assertEquals(moveFileAction.getDestinationPath(), moveFileAction.getParam2());
    }

}
