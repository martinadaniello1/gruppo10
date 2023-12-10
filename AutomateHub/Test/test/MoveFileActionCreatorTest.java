package test;

import automatehub.model_view.action.MoveFileAction;
import automatehub.model_view.action.creator.MoveFileActionCreator;
import static org.junit.Assert.*;
import org.junit.*;

public class MoveFileActionCreatorTest {

    private String startingPath;
    private String destinationPath;
    private MoveFileActionCreator creator;

    @Before
    public void setUp() {
        startingPath = "esempio/prova/prova.txt";
        destinationPath = "esempio2/prova2/prova.txt";
        creator = new MoveFileActionCreator(startingPath, destinationPath);
    }
    
    @Test
    public void testCreate() {
        MoveFileAction action = new MoveFileAction(startingPath, destinationPath);
        assertEquals(action, creator.create());
        
    }

}
