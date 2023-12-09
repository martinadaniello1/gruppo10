package test;

import automatehub.model_view.MoveFileAction;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;

public class MoveFileActionTest {

    private String startingPath;
    private String destinationPath;
    private MoveFileAction moveFileAction;

    @Before
    public void setUp() {
        startingPath = "./Test/test/testFiles/testDirectory2/file_to_move.jpg";
        destinationPath = "./Test/test/testFiles/testDirectory1";
        moveFileAction = new MoveFileAction(startingPath, destinationPath);
    }

    @Test
    public void testExecuteShouldMoveFileToDestinationDirectory() {

        int result = moveFileAction.execute();

        assertEquals(0, result);
        //Verify the presence of the file in the new directory
        File f = new File(destinationPath + "/file_to_move.jpg");
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
        assertEquals(startingPath, moveFileAction.getStartingPath());
    }
    @Test
    public void testSetStartingPath() {
        moveFileAction.setStartingPath("starting/Path");
        assertEquals("starting/Path", moveFileAction.getStartingPath());
    }

    @Test
    public void testGetDestinationPath() {
        assertEquals(destinationPath, moveFileAction.getDestinationPath());
    }

    @Test
    public void testSetDestinationPath() {
        moveFileAction.setDestinationPath("destination/Path");
        assertEquals("destination/Path", moveFileAction.getDestinationPath());
    }

    @Test
    public void testGetParam1() {
        assertEquals(moveFileAction.getStartingPath(), moveFileAction.getParam1());
    }

    @Test
    public void testGetParam2() {
        assertEquals(moveFileAction.getDestinationPath(), moveFileAction.getParam2());
    }
    
    @After
    public void resetDirectories(){
        String path = "./Test/test/testFiles/testDirectory1/file_to_move.jpg";
        File f = new File(path);
        if(f.exists()){
            try {
                FileUtils.moveFileToDirectory(f, new File("./Test/test/testFiles/testDirectory2"), false);
            } catch (IOException ex) {
                Logger.getLogger(MoveFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
